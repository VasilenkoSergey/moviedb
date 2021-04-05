package io.vasilenko.moviedb.ui.tvshows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.MockRepository
import io.vasilenko.moviedb.databinding.TvShowsFragmentBinding
import io.vasilenko.moviedb.ui.common.viewBinding

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {

    private val binding by viewBinding { TvShowsFragmentBinding.bind(it) }

    private val adapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val tvShows = MockRepository.getTvShows().map {
            TvShowItem(it)
        }.toList()
        binding.tvShowRecyclerView.adapter = adapter.apply {
            addAll(tvShows)
        }
    }
}
