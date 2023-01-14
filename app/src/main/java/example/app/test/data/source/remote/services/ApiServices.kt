package example.app.test.data.source.remote.services

import example.app.test.domain.model.remote.response.RestaurantList
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiServices {
    @GET("/v3/d0357273-832b-49dd-b210-e927fd08a02f")
    fun getRestaurantList(): Flow<RestaurantList>
}
