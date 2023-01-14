package example.app.test.domain.model.remote.response

import android.os.Parcelable
import androidx.room.Ignore
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantList(
    @Json(name = ("restaurants"))
    var restaurants: List<Restaurant>? = null
) : Parcelable

@Parcelize
data class Restaurant(
    @Json(name = ("name"))
    var name: String? = null,
    @Json(name = ("sortingValues"))
    var sortingValues: SortingValues? = null,
    @Json(name = ("status"))
    var status: String? = null,
    @Ignore
    var isFavorite: Boolean = false
) : Parcelable {
    val getStatus: Int
        get() {
            return if (isFavorite) {
                when (status) {
                    "open" -> 1
                    "order ahead" -> 2
                    else -> 3
                }
            } else {
                when (status) {
                    "open" -> 4
                    "order ahead" -> 5
                    else -> 6
                }
            }
        }
}

@Parcelize
data class SortingValues(
    @Json(name = ("averageProductPrice"))
    var averageProductPrice: Int? = null,
    @Json(name = ("bestMatch"))
    var bestMatch: Double? = null,
    @Json(name = ("deliveryCosts"))
    var deliveryCosts: Int? = null,
    @Json(name = ("distance"))
    var distance: Int? = null,
    @Json(name = ("minCost"))
    var minCost: Int? = null,
    @Json(name = ("newest"))
    var newest: Double? = null,
    @Json(name = ("popularity"))
    var popularity: Double? = null,
    @Json(name = ("ratingAverage"))
    var ratingAverage: Double? = null
) : Parcelable {
    val getDistance: String get() = "Distance : $distance meters"
    val getRate: String get() = "Rate : $ratingAverage"
    val getPopularity: String get() = "Popularity : $popularity"
    val getBestMatch: String get() = "Best match : $bestMatch"
    val getNewest: String get() = "Newest : $newest"
    val getDeliveryCosts: String get() = "Delivery Cost : $ $deliveryCosts"
    val getMinCosts: String get() = "Min Cost : $ $minCost"
    val getAverage: String get() = "AverageCost : $ $averageProductPrice"
}
