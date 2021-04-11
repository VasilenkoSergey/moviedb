package io.vasilenko.moviedb.data.network

import io.vasilenko.moviedb.data.network.dto.TvShowsResponseDto
import retrofit2.Call
import retrofit2.http.GET

interface TvShowsApi {

    @GET("tv/popular")
    fun getPopularTvShows(): Call<TvShowsResponseDto>
}