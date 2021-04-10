package io.vasilenko.moviedb.ui.feed

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.MockRepository
import io.vasilenko.moviedb.data.Movie
import io.vasilenko.moviedb.databinding.FeedFragmentBinding
import io.vasilenko.moviedb.ui.common.afterTextChanged
import io.vasilenko.moviedb.ui.common.viewBinding
import io.vasilenko.moviedb.ui.feed.FeedFragmentDirections.Companion.actionFeedToDetails
import timber.log.Timber

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private val binding by viewBinding { FeedFragmentBinding.bind(it) }

    private val adapter by lazy { GroupAdapter<GroupieViewHolder>() }

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.feedHeader.feedSearchToolbar.searchEditText.afterTextChanged {
            Timber.d(it.toString())
            if (it.toString().length > MIN_LENGTH) {
                openSearch(it.toString())
            }
        }

        val moviesList = listOf(
            MainCardContainer(
                R.string.recommended,
                MockRepository.getRecommendedMovies().map {
                    MovieItem(it) { movie ->
                        openMovieDetails(
                            movie
                        )
                    }
                }.toList()
            )
        )

        binding.moviesRecyclerView.adapter = adapter.apply { addAll(moviesList) }

        val newMoviesList = listOf(
            MainCardContainer(
                R.string.upcoming,
                MockRepository.getNewMovies().map {
                    MovieItem(it) { movie ->
                        openMovieDetails(movie)
                    }
                }.toList()
            )
        )

        adapter.apply { addAll(newMoviesList) }
    }

    private fun openMovieDetails(movie: Movie) {
        val action = actionFeedToDetails(movie.id)
        findNavController().navigate(action, options)
    }

    private fun openSearch(searchText: String) {
        val bundle = Bundle()
        bundle.putString(KEY_SEARCH, searchText)
        findNavController().navigate(R.id.search_dest, bundle, options)
    }

    override fun onStop() {
        super.onStop()
        binding.feedHeader.feedSearchToolbar.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    companion object {
        const val MIN_LENGTH = 3
        const val KEY_TITLE = "title"
        const val KEY_SEARCH = "search"
    }
}
