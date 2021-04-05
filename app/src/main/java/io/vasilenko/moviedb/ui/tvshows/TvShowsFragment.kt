package io.vasilenko.moviedb.ui.tvshows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.MockRepository
import kotlinx.android.synthetic.main.tv_shows_fragment.*

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val tvShows = MockRepository.getTvShows().map {
            TvShowItem(it)
        }.toList()
        tvShowRecyclerView.adapter = adapter.apply {
            addAll(tvShows)
        }
    }
}
