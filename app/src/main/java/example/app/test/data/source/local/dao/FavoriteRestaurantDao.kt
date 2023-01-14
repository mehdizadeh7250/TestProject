package example.app.test.data.source.local.dao

import androidx.room.*
import example.app.test.domain.model.entityModel.FavoriteEntity

@Dao
abstract class FavoriteRestaurantDao {

    @Query("SELECT * FROM favoriteTable")
    abstract fun all(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(favorite: FavoriteEntity)

    @Update
    abstract fun update(favorite: FavoriteEntity)

    @Delete
    abstract fun delete(favorite: FavoriteEntity)

    @Query("DELETE FROM favoriteTable WHERE name IN (:uName)")
    abstract fun delete(vararg uName: String)

}
