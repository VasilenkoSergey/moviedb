package io.vasilenko.moviedb.ui.tvshows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.vasilenko.moviedb.BuildConfig
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.Movie
import io.vasilenko.moviedb.data.PopularTvShowsRepository
import io.vasilenko.moviedb.databinding.TvShowsFragmentBinding
import io.vasilenko.moviedb.network.dto.TvShowsResponseDto
import io.vasilenko.moviedb.ui.common.viewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {

    private val binding by viewBinding { TvShowsFragmentBinding.bind(it) }

    private val adapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.tvShowRecyclerView.adapter = adapter
        PopularTvShowsRepository.getAll().enqueue(responseCallBack())
    }

    private fun responseCallBack(): Callback<TvShowsResponseDto> {
        return object : Callback<TvShowsResponseDto> {

            override fun onResponse(
                call: Call<TvShowsResponseDto>,
                response: Response<TvShowsResponseDto>
            ) {
                response.body()?.tvShows?.map {
                    TvShowItem(
                        Movie(
                            id = it.id,
                            title = it.title,
                            voteAverage = it.rating,
                            imagePath = it.posterPath?.let { path ->
                                BuildConfig.THE_MOVIE_DATABASE_POSTER_BASE_URL + path
                            }
                        )
                    )
                }?.let { tvShows ->
                    adapter.addAll(tvShows)
                }
            }

            override fun onFailure(call: Call<TvShowsResponseDto>, t: Throwable) {
                Timber.e(t)
            }
        }
    }
}
