package example.app.test.utils.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

open class DiffUtilBindingAdapter<M, B : ViewDataBinding>(
    appExecutors: AppExecutors,
    private val context: Context?,
    @LayoutRes val layout: IntArray,
    val onBindViewHolder: (
        item: M,
        position: Int,
        binder: B
    ) -> Unit,
    private val diffCallback: DiffUtil.ItemCallback<M>
) : ListAdapter<M, BinderViewHolderParent<M, B>>(
    AsyncDifferConfig.Builder(diffCallback)
        .setBackgroundThreadExecutor(appExecutors.diskIO())
        .build()
) {

    override fun getItemViewType(position: Int): Int {
        val o = getItem(position)
        return (o as? MultiViewType)?.itemViewType ?: 0
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BinderViewHolderParent<M, B> {
        val binding = DataBindingUtil.inflate<B>(
            LayoutInflater.from(context),
            layout[viewType],
            parent,
            false
        )
        return BinderViewHolderParent(
            binding,
            onBindViewHolder
        )
    }

    override fun onBindViewHolder(holder: BinderViewHolderParent<M, B>, position: Int) {
        getItem(position)?.let { item ->
            holder.onBindViewHolder.invoke(item, position, holder.binder)
        }
    }

    override fun getItemId(position: Int): Long = position.hashCode().toLong()
}
