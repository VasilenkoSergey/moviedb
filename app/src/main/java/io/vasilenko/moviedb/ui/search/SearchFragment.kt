package io.vasilenko.moviedb.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.ui.feed.FeedFragment.Companion.KEY_SEARCH
import kotlinx.android.synthetic.main.feed_header.*

class SearchFragment : Fragment(R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchTerm = requireArguments().getString(KEY_SEARCH)
        feedSearchToolbar.setText(searchTerm)
    }
}
