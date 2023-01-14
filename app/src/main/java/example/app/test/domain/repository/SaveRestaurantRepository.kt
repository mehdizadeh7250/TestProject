package example.app.test.domain.repository

import example.app.test.domain.model.entityModel.FavoriteEntity

interface SaveRestaurantRepository {
    fun insertFavorite(favoriteEntity: FavoriteEntity)
}