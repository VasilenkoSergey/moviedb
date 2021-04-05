package io.vasilenko.moviedb.ui.feed

import androidx.annotation.StringRes
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_card.*
import io.vasilenko.moviedb.R

class MainCardContainer(
    @StringRes
    private val title: Int,
    private val items: List<Item>
) : Item() {

    override fun getLayout() = R.layout.item_card

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.titleTextView.text = viewHolder.titleTextView.context.getString(title)
        viewHolder.itemsContainer.adapter =
            GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
    }
}
