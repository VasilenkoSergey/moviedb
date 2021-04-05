package io.vasilenko.moviedb.ui.tvshows

import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.Movie
import io.vasilenko.moviedb.databinding.TvShowItemBinding

class TvShowItem(
    private val movie: Movie
) : BindableItem<TvShowItemBinding>() {

    override fun initializeViewBinding(view: View): TvShowItemBinding {
        return TvShowItemBinding.bind(view)
    }

    override fun bind(binding: TvShowItemBinding, position: Int) {
        with(binding) {
            Picasso.get()
                .load(movie.imagePath)
                .into(tvShowImage)
            tvShowTitle.text = movie.title
            twShowRating.rating = movie.rating
        }
    }

    override fun getLayout() = R.layout.tv_show_item
}
