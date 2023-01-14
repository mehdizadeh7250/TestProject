package example.app.test.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import example.app.test.data.repository.ErrorHandlerImpl
import example.app.test.domain.repository.ErrorHandler

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorHandlerModule {
    @Binds
    abstract fun bindErrorHandler(errorHandlerImpl: ErrorHandlerImpl): ErrorHandler
}
