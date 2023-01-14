package example.app.test.domain.model.entityModel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favoriteTable")
@Parcelize
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo
    @Json(name = "name")
    var name: String? = null
) : Parcelable
