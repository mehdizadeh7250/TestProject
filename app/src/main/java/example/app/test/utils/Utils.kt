package example.app.test.utils

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import example.app.test.R
import example.app.test.domain.model.entityModel.FavoriteEntity
import example.app.test.domain.model.local.SortingTypes
import kotlinx.coroutines.flow.callbackFlow
import java.util.ArrayList
import java.util.concurrent.Flow

fun setTextSpinnerAdapter(
    context: Context,
    spinnerView: Spinner,
    items: List<String?>,
    onSelectedItemListener: ((item: Int) -> Unit)? = null
) {
    val indicator = ArrayList<String>(items.size)
    for (element in items) {
        element?.let { indicator.add(it) }
    }
    val spinnerAdapter = ArrayAdapter(
        context,
        R.layout.item_spinner_view,
        indicator
    )
    spinnerAdapter.setDropDownViewResource(R.layout.item_spinner_view)
    spinnerView.adapter = spinnerAdapter
    spinnerView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            p0: AdapterView<*>?,
            p1: View?,
            p2: Int,
            p3: Long
        ) {
            items[p2]?.let { item ->
                SortingTypes.values().findLast { it.sortingType == item }?.id
                onSelectedItemListener?.invoke(
                    SortingTypes.values().findLast { it.sortingType == item }?.id ?: 0
                )
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            items[0]?.let { item ->
                onSelectedItemListener?.invoke(
                    SortingTypes.values().findLast { it.sortingType == item }?.id ?: 0
                )
            }
        }
    }
}


