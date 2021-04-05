package io.vasilenko.moviedb.ui.feed

import android.view.View
import androidx.annotation.StringRes
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.databinding.ItemCardBinding

class MainCardContainer(
    @StringRes
    private val title: Int,
    private val items: List<BindableItem<*>>
) : BindableItem<ItemCardBinding>() {

    override fun initializeViewBinding(view: View): ItemCardBinding {
        return ItemCardBinding.bind(view)
    }

    override fun getLayout() = R.layout.item_card

    override fun bind(binding: ItemCardBinding, position: Int) {
        with(binding) {
            titleTextView.text = titleTextView.context.getString(title)
            itemsContainer.adapter =
                GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
        }
    }
}
