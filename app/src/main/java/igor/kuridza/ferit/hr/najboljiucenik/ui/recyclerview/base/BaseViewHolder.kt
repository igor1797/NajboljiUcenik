package igor.kuridza.ferit.hr.najboljiucenik.ui.recyclerview.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<T>(override val containerView: View): LayoutContainer, RecyclerView.ViewHolder(containerView){
    abstract fun bindItem(item: T)
}