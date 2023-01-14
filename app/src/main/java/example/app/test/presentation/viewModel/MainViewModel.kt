package example.app.test.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import example.app.test.domain.model.local.SortingTypes
import example.app.test.domain.model.remote.response.Restaurant
import example.app.test.domain.usecase.GetDataUseCase
import example.app.test.domain.usecase.RemoveRestaurantUseCase
import example.app.test.domain.usecase.SaveRestaurantDataUseCase
import example.app.test.presentation.base.BaseViewModel
import example.app.test.utils.ViewProgressHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val saveRestaurantUseCase: SaveRestaurantDataUseCase,
    private val removeRestaurantUseCase: RemoveRestaurantUseCase
) : BaseViewModel() {
    private val getRestaurantRes = MutableLiveData<List<Restaurant>>()
    val restaurantLiveData: LiveData<List<Restaurant>> get() = getRestaurantRes
    var sortedItem = SortingTypes.BestMatch.id
    fun getServerList(progress: ViewProgressHandler?) {
        progress?.showProgress()
        scope.launch {
            getDataUseCase.getData(
                onResult = {
                    getRestaurantRes.postValue(it)
                },
                onException = {
                    errorLiveData.postValue(it)
                    progress?.dismissProgress()
                },
                scope
            )
        }
    }

    fun saveRestaurant(restaurantName: String) =
        saveRestaurantUseCase.saveRestaurant(restaurantName)

    fun deleteRestaurant(restaurantName: String) =
        removeRestaurantUseCase.removeRestaurant(restaurantName)
}
