package io.vasilenko.moviedb.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.databinding.FragmentSearchBinding
import io.vasilenko.moviedb.ui.common.viewBinding
import io.vasilenko.moviedb.ui.feed.FeedFragment.Companion.KEY_SEARCH

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding by viewBinding { FragmentSearchBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchTerm = requireArguments().getString(KEY_SEARCH)
        binding.searchHeader.feedSearchToolbar.setText(searchTerm)
    }
}
