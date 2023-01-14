package example.app.test.domain.usecase.baseUseCae

import example.app.test.utils.Response
import kotlinx.coroutines.*
import timber.log.Timber

open class BaseUseCase {
    private var job: Job? = null
    private var coroutineScope: CoroutineScope? = CoroutineScope(Job() + Dispatchers.IO)
    private fun errorHandler(onFailure: ((message: String?) -> Unit)) =
        CoroutineExceptionHandler { context, error ->
            onFailure.invoke(error.message)
        }

    suspend fun <P> execute(
        doInBackGround: suspend (suspend () -> Unit) -> P,
        onResult: ((P) -> Unit),
        onException: ((message: String?) -> Unit)
    ) {
        job?.cancel()
        job = coroutineScope?.launch(errorHandler(onException)) {
            val result = doInBackGround {
            }
            launch(Dispatchers.Main) {
                onResult.invoke(result)
            }
        }
        job?.join()
    }

    fun <T> handleResponse(
        response: Response<T>,
        onError: ((errorId: String) -> Unit)? = null
    ): T? {
        return when (response) {
            is Response.ErrorState -> {
                Timber.tag("Response").d("Error")
                onError?.invoke(response.exception.message)
                null
            }
            is Response.LoadingState -> {
                Timber.tag("Response").d("Starting")
                null
            }
            is Response.SuccessState -> {
                response.data
            }
        }
    }
}
