package io.vasilenko.moviedb.network

import io.vasilenko.moviedb.network.dto.MoviesResponseDto
import retrofit2.Call
import retrofit2.http.GET

interface MoviesApi {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(): Call<MoviesResponseDto>

    @GET("movie/upcoming")
    fun getUpcomingMovies(): Call<MoviesResponseDto>

    @GET("movie/popular")
    fun getPopularMovies(): Call<MoviesResponseDto>
}
