package io.vasilenko.moviedb.ui.tvshows

import android.os.Bundle
import android.view.View
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.mapper.PopularTvShowsMapper
import io.vasilenko.moviedb.data.repository.PopularTvShowsRepository
import io.vasilenko.moviedb.databinding.TvShowsFragmentBinding
import io.vasilenko.moviedb.ui.common.BaseFragment
import io.vasilenko.moviedb.ui.common.applySchedulers
import io.vasilenko.moviedb.ui.common.viewBinding
import timber.log.Timber

class TvShowsFragment : BaseFragment(R.layout.tv_shows_fragment) {

    private val binding by viewBinding { TvShowsFragmentBinding.bind(it) }

    private val adapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.tvShowRecyclerView.adapter = adapter
        addDisposable(
            PopularTvShowsRepository.getAll()
                .applySchedulers()
                .subscribe({
                    adapter.addAll(
                        PopularTvShowsMapper.mapTvShowsModelsToItems(it)
                    )
                }, {
                    Timber.e(it)
                })
        )
    }
}
