package example.app.test.domain.repository

import example.app.test.domain.model.remote.response.RestaurantList
import example.app.test.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetRemoteRestaurantListRepository {
    suspend fun getRemoteList(): Flow<Response<RestaurantList>>
}
