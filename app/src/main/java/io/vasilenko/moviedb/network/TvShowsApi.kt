package io.vasilenko.moviedb.network

import io.vasilenko.moviedb.network.dto.TvShowsResponseDto
import retrofit2.Call
import retrofit2.http.GET

interface TvShowsApi {

    @GET("tv/popular")
    fun getPopularTvShows(): Call<TvShowsResponseDto>
}