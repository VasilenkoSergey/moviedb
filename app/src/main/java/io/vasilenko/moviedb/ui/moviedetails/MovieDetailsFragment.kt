package io.vasilenko.moviedb.ui.moviedetails

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.Actor
import io.vasilenko.moviedb.data.MovieDetails
import io.vasilenko.moviedb.data.mapper.MovieDetailsMapper
import io.vasilenko.moviedb.data.repository.MovieDetailsRepository
import io.vasilenko.moviedb.databinding.MovieDetailsFragmentBinding
import io.vasilenko.moviedb.ui.common.BaseFragment
import io.vasilenko.moviedb.ui.common.applySchedulers
import io.vasilenko.moviedb.ui.common.load
import io.vasilenko.moviedb.ui.common.viewBinding
import timber.log.Timber

class MovieDetailsFragment : BaseFragment(R.layout.movie_details_fragment) {

    private val binding by viewBinding { MovieDetailsFragmentBinding.bind(it) }

    private val args: MovieDetailsFragmentArgs by navArgs()

    private val adapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val id = args.id

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        addDisposable(
            MovieDetailsRepository.getById(id)
                .applySchedulers()
                .subscribe(
                    {
                        renderMovieDetails(MovieDetailsMapper.mapMovieDtoToModel(it))
                    },
                    {
                        Timber.e(it)
                    }
                )
        )

        addDisposable(
            MovieDetailsRepository.getCreditsById(id)
                .applySchedulers()
                .subscribe(
                    {
                        renderActors(it)
                    },
                    {
                        Timber.e(it)
                    }
                )
        )
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

        val space = resources.getDimensionPixelSize(R.dimen.material_margin_large)

        with(binding) {
            actorsRecyclerView.addItemDecoration(
                ActorsItemDecoration(space)
            )
            actorsRecyclerView.adapter =
                adapter.apply { addAll(MovieDetailsMapper.mapActorModelsToItems(actors)) }
        }
    }
}
