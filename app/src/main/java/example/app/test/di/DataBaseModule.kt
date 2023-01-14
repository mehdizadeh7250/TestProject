package example.app.test.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import example.app.test.data.repository.GetLocalRestaurantRepositoryImpl
import example.app.test.data.repository.RemoveRestaurantRepositoryImpl
import example.app.test.data.repository.SaveRestaurantRepositoryImpl
import example.app.test.data.source.local.AppDataBase
import example.app.test.data.source.local.dao.FavoriteRestaurantDao
import example.app.test.domain.repository.GetLocalRestaurantRepository
import example.app.test.domain.repository.RemoveRestaurantRepository
import example.app.test.domain.repository.SaveRestaurantRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, "app-db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideProfileDao(appDatabase: AppDataBase) = appDatabase.provideRestaurantDao()

    @Provides
    fun provideGetLocalData(favoriteRestaurantDao: FavoriteRestaurantDao): GetLocalRestaurantRepository =
        GetLocalRestaurantRepositoryImpl(favoriteRestaurantDao)

    @Provides
    fun provideSaveRestaurant(favoriteRestaurantDao: FavoriteRestaurantDao): SaveRestaurantRepository =
        SaveRestaurantRepositoryImpl(favoriteRestaurantDao)

    @Provides
    fun provideRemoveRestaurant(favoriteRestaurantDao: FavoriteRestaurantDao): RemoveRestaurantRepository =
        RemoveRestaurantRepositoryImpl(favoriteRestaurantDao)
}
