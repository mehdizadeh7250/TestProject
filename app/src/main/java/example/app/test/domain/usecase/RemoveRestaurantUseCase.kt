package example.app.test.domain.usecase

import example.app.test.domain.repository.RemoveRestaurantRepository
import javax.inject.Inject

class RemoveRestaurantUseCase @Inject constructor(
    private val removeRestaurantRepository: RemoveRestaurantRepository
) {
    fun removeRestaurant(restaurantName: String) =
        removeRestaurantRepository.deleteFavRestaurant(restaurantName)
}
