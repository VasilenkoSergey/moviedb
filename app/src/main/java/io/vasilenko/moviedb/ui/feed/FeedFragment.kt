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
import io.vasilenko.moviedb.BuildConfig
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.*
import io.vasilenko.moviedb.databinding.FeedFragmentBinding
import io.vasilenko.moviedb.network.dto.MoviesResponseDto
import io.vasilenko.moviedb.ui.common.afterTextChanged
import io.vasilenko.moviedb.ui.common.viewBinding
import io.vasilenko.moviedb.ui.feed.FeedFragmentDirections.Companion.actionFeedToDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        binding.moviesRecyclerView.adapter = adapter.apply {
            if (itemCount == 0) {
                NowPlayingMoviesRepository.getAll().enqueue(responseCallback(R.string.recommended))
                UpcomingMoviesRepository.getAll().enqueue(responseCallback(R.string.upcoming))
                PopularMoviesRepository.getAll().enqueue(responseCallback(R.string.popular))
            }
        }
    }

    private fun responseCallback(title: Int): Callback<MoviesResponseDto> {
        return object : Callback<MoviesResponseDto> {

            override fun onResponse(
                call: Call<MoviesResponseDto>,
                response: Response<MoviesResponseDto>
            ) {
                response.body()?.movies?.map {
                    MovieItem(
                        Movie(
                            id = it.id.toLong(),
                            title = it.title,
                            voteAverage = it.rating,
                            imagePath = it.posterPath?.let { path ->
                                BuildConfig.THE_MOVIE_DATABASE_POSTER_BASE_URL + path
                            }
                        )
                    ) { movie ->
                        openMovieDetails(movie)
                    }
                }?.let { movies ->
                    adapter.add(MainCardContainer(title, movies))
                }
            }

            override fun onFailure(call: Call<MoviesResponseDto>, t: Throwable) {
                Timber.e(t)
            }
        }
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
