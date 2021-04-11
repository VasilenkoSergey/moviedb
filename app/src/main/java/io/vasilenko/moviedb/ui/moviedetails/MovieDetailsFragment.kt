package io.vasilenko.moviedb.ui.moviedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.vasilenko.moviedb.BuildConfig
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.Actor
import io.vasilenko.moviedb.data.MovieDetails
import io.vasilenko.moviedb.data.MovieDetailsRepository
import io.vasilenko.moviedb.databinding.MovieDetailsFragmentBinding
import io.vasilenko.moviedb.network.dto.MovieCreditsDto
import io.vasilenko.moviedb.network.dto.MovieDto
import io.vasilenko.moviedb.ui.common.load
import io.vasilenko.moviedb.ui.common.viewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private val binding by viewBinding { MovieDetailsFragmentBinding.bind(it) }

    private val args: MovieDetailsFragmentArgs by navArgs()

    private val adapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val id = args.id.toInt()

        with(binding) {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        MovieDetailsRepository.getById(id).enqueue(movieDetailsCallback())
        MovieDetailsRepository.getCreditsById(id).enqueue(movieCreditsCallback())
    }

    private fun movieDetailsCallback(): Callback<MovieDto> {
        return object : Callback<MovieDto> {

            override fun onResponse(call: Call<MovieDto>, response: Response<MovieDto>) {
                response.body()?.let {
                    renderMovieDetails(
                        MovieDetails(
                            id = it.id.toLong(),
                            title = it.title,
                            description = it.overview,
                            voteAverage = it.rating,
                            imageBackdropPath = it.backdropPath?.let { path ->
                                BuildConfig.THE_MOVIE_DATABASE_BACKDROP_BASE_URL + path
                            },
                            studio = it.productionCompanies?.joinToString { company ->
                                "${company.name}"
                            },
                            genre = it.genres?.joinToString { genre ->
                                "${genre.name}"
                            },
                            year = it.releaseDate
                        )
                    )
                }
            }

            override fun onFailure(call: Call<MovieDto>, t: Throwable) {
                Timber.e(t)
            }
        }
    }

    private fun movieCreditsCallback(): Callback<MovieCreditsDto> {
        return object : Callback<MovieCreditsDto> {

            override fun onResponse(
                call: Call<MovieCreditsDto>,
                response: Response<MovieCreditsDto>
            ) {
                response.body()?.cast?.map {
                    Actor(
                        name = it.name,
                        imagePath = it.profilePath?.let { path ->
                            BuildConfig.THE_MOVIE_DATABASE_PROFILE_BASE_URL + path
                        }
                    )
                }?.let {
                    renderActors(it)
                }
            }

            override fun onFailure(call: Call<MovieCreditsDto>, t: Throwable) {
                Timber.e(t)
            }
        }
    }

    private fun renderMovieDetails(movie: MovieDetails) {
        with(binding) {
            movie.imageBackdropPath?.let { movieBackdropImage.load(it) }
            movieTitle.text = movie.title
            movieRating.rating = movie.rating
            movieDescription.text = movie.description
            studioTitle.text = movie.studio
            genreTitle.text = movie.genre
            yearTitle.text = movie.year
        }
    }

    private fun renderActors(actors: List<Actor>) {
        val actorItems = actors.map {
            ActorItem(it)
        }.toList()

        val space = resources.getDimensionPixelSize(R.dimen.material_margin_large)

        with(binding) {
            actorsRecyclerView.addItemDecoration(
                ActorsItemDecoration(space)
            )
            actorsRecyclerView.adapter = adapter.apply { addAll(actorItems) }
        }
    }
}
