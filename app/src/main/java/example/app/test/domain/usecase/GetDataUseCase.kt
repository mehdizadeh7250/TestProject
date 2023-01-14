package example.app.test.domain.usecase

import example.app.test.domain.model.entityModel.FavoriteEntity
import example.app.test.domain.model.remote.response.Restaurant
import example.app.test.domain.repository.GetLocalRestaurantRepository
import example.app.test.domain.repository.GetRemoteRestaurantListRepository
import example.app.test.domain.usecase.baseUseCae.BaseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetDataUseCase @Inject constructor(
    private val getRemoteRestaurantListRepository: GetRemoteRestaurantListRepository,
    private val getLocalRestaurantListRepository: GetLocalRestaurantRepository
) : BaseUseCase() {
    private suspend fun getRemoteList() = getRemoteRestaurantListRepository.getRemoteList()
    private fun getLocalData() = getLocalRestaurantListRepository.getLocalData()
    suspend fun executeGetRemoteList(
        onResult: ((List<Restaurant>) -> Unit)? = null,
        onException: ((String) -> Unit)? = null,
        lifecycleCoroutineScope: CoroutineScope? = null
    ) {
        execute(
            doInBackGround = {
                getRemoteList()
            },
            onResult = { flow ->
                lifecycleCoroutineScope?.launch {
                    flow.catch {
                        onException?.invoke(it.message.toString())
                    }.collect { res ->
                        val response = handleResponse(res) {
                            onException?.invoke(it)
                        }
                        response?.let {
                            it.restaurants?.let { it1 -> onResult?.invoke(it1) }
                        }
                    }
                }
            },
            onException = {
                it?.let {
                    onException?.invoke(it)
                }
            }
        )
    }

    private fun changeResult(
        localData: List<FavoriteEntity>,
        remoteData: List<Restaurant>
    ): List<Restaurant> {
        remoteData.forEach {
            val find = localData.indexOfLast { fav -> fav.name == it.name }
            if (find >= 0) {
                it.apply {
                    isFavorite = true
                }
            }
        }
        return remoteData
    }

    fun getData(
        onResult: ((List<Restaurant>) -> Unit)? = null,
        onException: ((String) -> Unit)? = null,
        lifecycleCoroutineScope: CoroutineScope? = null
    ) {
        lifecycleCoroutineScope?.launch {
            val localData = async { getLocalData() }
            executeGetRemoteList(
                onResult = { remoteResult ->
                    lifecycleCoroutineScope.launch {
                        val result = localData.await()
                        onResult?.invoke(changeResult(result, remoteResult))
                    }
                },
                onException = {
                    onException?.invoke(it)
                },
                lifecycleCoroutineScope
            )
        }
    }
}
