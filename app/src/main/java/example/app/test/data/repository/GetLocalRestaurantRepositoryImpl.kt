package example.app.test.data.repository

import example.app.test.data.source.local.dao.FavoriteRestaurantDao
import example.app.test.domain.model.entityModel.FavoriteEntity
import example.app.test.domain.repository.GetLocalRestaurantRepository
import javax.inject.Inject

class GetLocalRestaurantRepositoryImpl @Inject constructor(
    private val favoriteRestaurantDao: FavoriteRestaurantDao
) : GetLocalRestaurantRepository {
    override fun getLocalData() = favoriteRestaurantDao.all()

}
