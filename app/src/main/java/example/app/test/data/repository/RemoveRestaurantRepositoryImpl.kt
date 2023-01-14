package example.app.test.data.repository

import example.app.test.data.source.local.dao.FavoriteRestaurantDao
import example.app.test.domain.repository.RemoveRestaurantRepository
import javax.inject.Inject

class RemoveRestaurantRepositoryImpl @Inject constructor(
    private val favoriteRestaurantDao: FavoriteRestaurantDao
) : RemoveRestaurantRepository {
    override fun deleteFavRestaurant(restaurantName: String) =
        favoriteRestaurantDao.delete(restaurantName)
}
