package io.vasilenko.moviedb.data.network

import io.reactivex.Single
import io.vasilenko.moviedb.data.network.dto.TvShowsResponseDto
import retrofit2.http.GET

interface TvShowsApi {

    @GET("tv/popular")
    fun getPopularTvShows(): Single<TvShowsResponseDto>
}
