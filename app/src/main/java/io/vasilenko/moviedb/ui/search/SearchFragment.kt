package io.vasilenko.moviedb.ui.search

import android.os.Bundle
import android.view.View
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.databinding.FragmentSearchBinding
import io.vasilenko.moviedb.ui.common.BaseFragment
import io.vasilenko.moviedb.ui.common.applySchedulers
import io.vasilenko.moviedb.ui.common.viewBinding
import io.vasilenko.moviedb.ui.feed.FeedFragment.Companion.KEY_SEARCH
import timber.log.Timber

class SearchFragment : BaseFragment(R.layout.fragment_search) {

    private val binding by viewBinding { FragmentSearchBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val searchTerm = requireArguments().getString(KEY_SEARCH)
        binding.searchHeader.feedSearchToolbar.setText(searchTerm)

        addDisposable(
            binding.searchHeader.feedSearchToolbar.getSearchText()
                .applySchedulers()
                .subscribe({ text ->
                    Timber.d("Search text: $text")
                }, {
                    Timber.e(it)
                })
        )
    }
}
