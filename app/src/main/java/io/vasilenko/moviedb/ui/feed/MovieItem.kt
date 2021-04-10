package io.vasilenko.moviedb.ui.feed

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.Movie
import io.vasilenko.moviedb.databinding.ItemWithTextBinding
import io.vasilenko.moviedb.ui.common.load

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
            movie.imagePath?.let { imagePreview.load(it) }
            content.setOnClickListener {
                onClick.invoke(movie)
            }
        }
    }
}
