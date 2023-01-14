package example.app.test.data.repository

import example.app.test.data.source.remote.services.ApiServices
import example.app.test.domain.repository.ErrorHandler
import example.app.test.domain.repository.GetRemoteRestaurantListRepository
import example.app.test.utils.toResult
import javax.inject.Inject

class GetRemoteRestaurantListRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val errorHandler: ErrorHandler
) : GetRemoteRestaurantListRepository {
    override suspend fun getRemoteList() = apiServices.getRestaurantList().toResult(errorHandler)
}
