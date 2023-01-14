package example.app.test.data.repository

import example.app.test.data.source.local.dao.FavoriteRestaurantDao
import example.app.test.domain.model.entityModel.FavoriteEntity
import example.app.test.domain.repository.SaveRestaurantRepository
import javax.inject.Inject

class SaveRestaurantRepositoryImpl @Inject constructor(
    private val favoriteRestaurantDao: FavoriteRestaurantDao
) : SaveRestaurantRepository {
    override fun insertFavorite(favoriteEntity: FavoriteEntity) =
        favoriteRestaurantDao.insert(favoriteEntity)
}
