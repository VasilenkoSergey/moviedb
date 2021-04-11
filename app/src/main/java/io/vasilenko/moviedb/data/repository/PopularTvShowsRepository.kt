package io.vasilenko.moviedb.data.repository

import io.vasilenko.moviedb.data.network.NetworkProvider
import io.vasilenko.moviedb.data.network.dto.TvShowsResponseDto
import retrofit2.Call

object PopularTvShowsRepository {

    fun getAll(): Call<TvShowsResponseDto> = NetworkProvider.tvShowsApi().getPopularTvShows()
}