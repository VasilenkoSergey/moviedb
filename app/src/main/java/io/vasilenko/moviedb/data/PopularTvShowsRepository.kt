package io.vasilenko.moviedb.data

import io.vasilenko.moviedb.network.MovieApiClient
import io.vasilenko.moviedb.network.dto.TvShowsResponseDto
import retrofit2.Call

object PopularTvShowsRepository {

    fun getAll(): Call<TvShowsResponseDto> = MovieApiClient.tvShowsApi().getPopularTvShows()
}