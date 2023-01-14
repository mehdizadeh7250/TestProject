package example.app.test.domain.usecase

import example.app.test.domain.model.entityModel.FavoriteEntity
import example.app.test.domain.repository.SaveRestaurantRepository
import javax.inject.Inject

class SaveRestaurantDataUseCase @Inject constructor(
    private val saveRestaurantRepository: SaveRestaurantRepository
) {
    fun saveRestaurant(restaurantName: String) = saveRestaurantRepository.insertFavorite(
        FavoriteEntity(name = restaurantName)
    )
}
