package io.vasilenko.moviedb.ui.watchlist

import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text.*
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.Movie

class MoviePreviewItem(
    private val content: Movie,
    private val onClick: (movie: Movie) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_small

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.imagePreview.setOnClickListener {
            onClick.invoke(content)
        }
        Picasso.get()
            .load(content.imagePath)
            .into(viewHolder.imagePreview)
    }
}
