package io.vasilenko.moviedb.data.network

import io.vasilenko.moviedb.data.network.dto.MovieCreditsDto
import io.vasilenko.moviedb.data.network.dto.MovieDto
import io.vasilenko.moviedb.data.network.dto.MoviesResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(): Call<MoviesResponseDto>

    @GET("movie/upcoming")
    fun getUpcomingMovies(): Call<MoviesResponseDto>

    @GET("movie/popular")
    fun getPopularMovies(): Call<MoviesResponseDto>

    @GET("movie/{id}")
    fun getMovie(@Path("id") id: Int): Call<MovieDto>

    @GET("movie/{id}/credits")
    fun getMovieCredits(@Path("id") id: Int): Call<MovieCreditsDto>
}
