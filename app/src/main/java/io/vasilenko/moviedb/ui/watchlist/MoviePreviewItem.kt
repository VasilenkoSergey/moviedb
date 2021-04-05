package io.vasilenko.moviedb.ui.watchlist

import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.Movie
import io.vasilenko.moviedb.databinding.ItemSmallBinding

class MoviePreviewItem(
    private val movie: Movie,
    private val onClick: (movie: Movie) -> Unit
) : BindableItem<ItemSmallBinding>() {

    override fun initializeViewBinding(view: View): ItemSmallBinding {
        return ItemSmallBinding.bind(view)
    }

    override fun getLayout() = R.layout.item_small

    override fun bind(binding: ItemSmallBinding, position: Int) {
        with(binding) {
            imagePreview.setOnClickListener {
                onClick.invoke(movie)
            }
            Picasso.get()
                .load(movie.imagePath)
                .into(imagePreview)
        }
    }
}
