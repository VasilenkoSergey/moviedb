package io.vasilenko.moviedb.ui.tvshows

import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.Movie
import kotlinx.android.synthetic.main.tv_show_item.*
import java.util.*

class TvShowItem(
    private val content: Movie
): Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        Picasso.get()
            .load(content.imagePath)
            .into(viewHolder.tvShowImage)
        viewHolder.tvShowTitle.text = content.title?.toUpperCase(Locale.ROOT)
        viewHolder.twShowRating.rating = content.rating
    }

    override fun getLayout() = R.layout.tv_show_item
}