package example.app.test.domain.repository

import example.app.test.domain.model.local.ErrorEntity

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}