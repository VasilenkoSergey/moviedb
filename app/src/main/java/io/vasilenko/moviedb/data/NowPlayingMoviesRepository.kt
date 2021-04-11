package io.vasilenko.moviedb.data

import io.vasilenko.moviedb.network.MovieApiClient
import io.vasilenko.moviedb.network.dto.MoviesResponseDto
import retrofit2.Call

object NowPlayingMoviesRepository {

    fun getAll(): Call<MoviesResponseDto> =
        MovieApiClient.moviesApi().getNowPlayingMovies()
}
