package example.app.test.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import example.app.test.data.source.local.dao.FavoriteRestaurantDao
import example.app.test.domain.model.entityModel.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun provideRestaurantDao(): FavoriteRestaurantDao
}
