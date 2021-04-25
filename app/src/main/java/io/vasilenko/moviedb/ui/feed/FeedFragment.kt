package io.vasilenko.moviedb.ui.feed

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.StringRes
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.Single.zip
import io.reactivex.functions.Function3
import io.vasilenko.moviedb.BuildConfig
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.FeedMovies
import io.vasilenko.moviedb.data.Movie
import io.vasilenko.moviedb.data.network.dto.MovieDto
import io.vasilenko.moviedb.data.network.dto.MoviesResponseDto
import io.vasilenko.moviedb.data.repository.NowPlayingMoviesRepository
import io.vasilenko.moviedb.data.repository.PopularMoviesRepository
import io.vasilenko.moviedb.data.repository.UpcomingMoviesRepository
import io.vasilenko.moviedb.databinding.FeedFragmentBinding
import io.vasilenko.moviedb.ui.common.BaseFragment
import io.vasilenko.moviedb.ui.common.applySchedulers
import io.vasilenko.moviedb.ui.common.viewBinding
import io.vasilenko.moviedb.ui.feed.FeedFragmentDirections.Companion.actionFeedToDetails
import timber.log.Timber

class FeedFragment : BaseFragment(R.layout.feed_fragment) {

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
        addDisposable(
            binding.feedHeader.feedSearchToolbar.getSearchText()
                .applySchedulers()
                .subscribe({ text ->
                    Timber.d("Search text: $text")
                    openSearch(text)
                }, {
                    Timber.e(it)
                })
        )

        binding.moviesRecyclerView.adapter = adapter.apply {
            if (itemCount == 0) {
                addDisposable(zip(
                    NowPlayingMoviesRepository.getAll(),
                    UpcomingMoviesRepository.getAll(),
                    PopularMoviesRepository.getAll(),
                    Function3<MoviesResponseDto, MoviesResponseDto, MoviesResponseDto, FeedMovies>
                    { nowPlaying, upcoming, popular ->
                        FeedMovies(
                            nowPlayingMovies = nowPlaying.movies,
                            upcomingPlayingMovies = upcoming.movies,
                            popularMovies = popular.movies
                        )
                    }
                ).applySchedulers()
                    .doOnSubscribe { binding.progressBar.visibility = VISIBLE }
                    .doFinally { binding.progressBar.visibility = GONE }
                    .subscribe({
                        renderMovies(it.nowPlayingMovies, R.string.recommended)
                        renderMovies(it.upcomingPlayingMovies, R.string.upcoming)
                        renderMovies(it.popularMovies, R.string.popular)
                    }, {
                        Timber.e(it)
                    })
                )
            }
        }
    }

    private fun renderMovies(movies: List<MovieDto>, @StringRes title: Int) {
        movies.map {
            MovieItem(
                Movie(
                    id = it.id,
                    title = it.title,
                    voteAverage = it.rating,
                    imagePath = it.posterPath?.let { path ->
                        BuildConfig.THE_MOVIE_DATABASE_POSTER_BASE_URL + path
                    }
                )
            ) { movie ->
                openMovieDetails(movie)
            }
        }.let {
            adapter.add(MainCardContainer(title, it))
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
        const val KEY_SEARCH = "search"
    }
}
