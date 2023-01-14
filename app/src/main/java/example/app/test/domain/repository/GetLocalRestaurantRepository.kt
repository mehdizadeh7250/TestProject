package example.app.test.domain.repository

import example.app.test.domain.model.entityModel.FavoriteEntity

interface GetLocalRestaurantRepository {
    fun getLocalData(): List<FavoriteEntity>
}
