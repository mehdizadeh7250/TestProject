package example.app.test.utils

import example.app.test.domain.repository.ErrorHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import timber.log.Timber

fun <T> Flow<T>.toResult(errorHandler: ErrorHandler): Flow<Response<T>> = this.map<T, Response<T>> {
    Timber.tag("ResultState").d("SuccessState")
    Response.SuccessState(it)
}.onStart {
    Timber.tag("ResultState").d("StartState")
    emit(Response.LoadingState)
}.catch {
    Timber.tag("ResultState").d("ErrorState")
    emit(Response.ErrorState(errorHandler.getError(it)))
}

