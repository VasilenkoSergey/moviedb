package io.vasilenko.moviedb.data.network

import io.reactivex.Single
import io.vasilenko.moviedb.data.network.dto.MovieCreditsDto
import io.vasilenko.moviedb.data.network.dto.MovieDto
import io.vasilenko.moviedb.data.network.dto.MoviesResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(): Single<MoviesResponseDto>

    @GET("movie/upcoming")
    fun getUpcomingMovies(): Single<MoviesResponseDto>

    @GET("movie/popular")
    fun getPopularMovies(): Single<MoviesResponseDto>

    @GET("movie/{id}")
    fun getMovie(@Path("id") id: Int): Single<MovieDto>

    @GET("movie/{id}/credits")
    fun getMovieCredits(@Path("id") id: Int): Single<MovieCreditsDto>
}
