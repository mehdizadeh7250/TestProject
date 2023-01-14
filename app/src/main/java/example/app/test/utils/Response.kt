package example.app.test.utils

import example.app.test.domain.model.local.ErrorEntity

sealed class Response<out T> {
    object LoadingState : Response<Nothing>()
    data class ErrorState(var exception: ErrorEntity) : Response<Nothing>()
    data class SuccessState<T>(var data: T) : Response<T>()
}