package io.vasilenko.moviedb.ui.moviedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.MockRepository
import io.vasilenko.moviedb.databinding.MovieDetailsFragmentBinding
import io.vasilenko.moviedb.ui.common.viewBinding
import timber.log.Timber

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private val binding by viewBinding { MovieDetailsFragmentBinding.bind(it) }

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val movie = MockRepository.getMovieById(args.id)
        Timber.d("MovieDetailsFragment movie: %s", movie)
    }
}
