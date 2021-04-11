package io.vasilenko.moviedb.ui.watchlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.local.MockRepository
import io.vasilenko.moviedb.databinding.FragmentWatchlistBinding
import io.vasilenko.moviedb.ui.common.viewBinding

class WatchlistFragment : Fragment(R.layout.fragment_watchlist) {

    private val binding by viewBinding { FragmentWatchlistBinding.bind(it) }

    private val adapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val moviesList =
            MockRepository.getWatchlistMovies().map {
                MoviePreviewItem(
                    it
                ) { movie -> }
            }.toList()

        with(binding) {
            watchlistMoviesRecyclerView.layoutManager = GridLayoutManager(context, 4)
            watchlistMoviesRecyclerView.adapter = adapter.apply { addAll(moviesList) }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = WatchlistFragment()
    }
}
