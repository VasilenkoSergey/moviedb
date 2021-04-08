package io.vasilenko.moviedb.ui.moviedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.MockRepository
import io.vasilenko.moviedb.databinding.MovieDetailsFragmentBinding
import io.vasilenko.moviedb.ui.common.load
import io.vasilenko.moviedb.ui.common.viewBinding

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private val binding by viewBinding { MovieDetailsFragmentBinding.bind(it) }

    private val args: MovieDetailsFragmentArgs by navArgs()

    private val adapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val movie = MockRepository.getMovieById(args.id)
        val actors = movie.actors.map {
            ActorItem(it)
        }.toList()

        with(binding) {
            toolbar.inflateMenu(R.menu.movie_details_menu)
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            movie.imageBackdropPath?.let { movieBackdropImage.load(it) }
            movieTitle.text = movie.title
            movieRating.rating = movie.rating
            movieDescription.text = movie.description
            studioTitle.text = movie.studio
            genreTitle.text = movie.genre
            yearTitle.text = movie.year

            val space = resources.getDimensionPixelSize(R.dimen.material_margin_large)
            actorsRecyclerView.addItemDecoration(
                ActorsItemDecoration(space)
            )
            actorsRecyclerView.adapter = adapter.apply { addAll(actors) }
        }
    }
}
