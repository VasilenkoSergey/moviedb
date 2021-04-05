package io.vasilenko.moviedb.ui.feed

import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.Movie
import io.vasilenko.moviedb.databinding.ItemWithTextBinding

class MovieItem(
    private val movie: Movie,
    private val onClick: (movie: Movie) -> Unit
) : BindableItem<ItemWithTextBinding>() {

    override fun initializeViewBinding(view: View): ItemWithTextBinding {
        return ItemWithTextBinding.bind(view)
    }

    override fun getLayout() = R.layout.item_with_text

    override fun bind(binding: ItemWithTextBinding, position: Int) {
        with(binding) {
            description.text = movie.title
            movieRating.rating = movie.rating
            content.setOnClickListener {
                onClick.invoke(movie)
            }

            Picasso.get()
                .load(movie.imagePath)
                .into(imagePreview)
        }
    }
}
