package example.app.test.utils.helper

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BinderViewHolderParent<M, B : ViewDataBinding>(
    val binder: B,
    val onBindViewHolder: (
        item: M,
        position: Int,
        binder: B
    ) -> Unit
) : RecyclerView.ViewHolder(binder.root)
