package example.app.test.utils

import example.app.test.domain.model.remote.response.Restaurant

fun mergeSort(list: List<Restaurant>): List<Restaurant> {
    if (list.size <= 1) {
        return list
    }

    val middle = list.size / 2
    val left = list.subList(0, middle)
    val right = list.subList(middle, list.size)

    return merge(mergeSort(left), mergeSort(right))
}

private fun merge(left: List<Restaurant>, right: List<Restaurant>): ArrayList<Restaurant> {
    var indexLeft = 0
    var indexRight = 0
    val newList: ArrayList<Restaurant> = arrayListOf()

    while (indexLeft < left.count() && indexRight < right.count()) {
        if (left[indexLeft].getStatus <= right[indexRight].getStatus) {
            newList.add(left[indexLeft])
            indexLeft++
        } else {
            newList.add(right[indexRight])
            indexRight++
        }
    }

    while (indexLeft < left.size) {
        newList.add(left[indexLeft])
        indexLeft++
    }

    while (indexRight < right.size) {
        newList.add(right[indexRight])
        indexRight++
    }
    return newList
}
