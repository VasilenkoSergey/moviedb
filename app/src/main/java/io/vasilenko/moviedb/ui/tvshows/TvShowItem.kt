package io.vasilenko.moviedb.ui.tvshows

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.TvShow
import io.vasilenko.moviedb.databinding.TvShowItemBinding
import io.vasilenko.moviedb.ui.common.load

class TvShowItem(
    private val tvShow: TvShow
) : BindableItem<TvShowItemBinding>() {

    override fun initializeViewBinding(view: View): TvShowItemBinding {
        return TvShowItemBinding.bind(view)
    }

    override fun bind(binding: TvShowItemBinding, position: Int) {
        with(binding) {
            tvShow.imagePath?.let { tvShowImage.load(it) }
            tvShowTitle.text = tvShow.name
            twShowRating.rating = tvShow.rating
        }
    }

    override fun getLayout() = R.layout.tv_show_item
}
