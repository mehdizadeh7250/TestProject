package example.app.test.domain.model.local

enum class SortingTypes(val id: Int, val sortingType: String) {
    BestMatch(
        0,
        "Best Match"
    ),
    AverageProductPrice(
        1,
        "Average Product Price"
    ),
    DeliveryCosts(
        2,
        "Delivery Costs"
    ),
    Distance(
        3,
        "Distance "
    ),
    MinCost(
        4,
        "Min Cost"
    ),
    Newest(
        5,
        "Newest"
    ),
    Popularity(
        6,
        "Popularity"
    ),
    RatingAverage(
        7,
        "Rating Average"
    )
}
