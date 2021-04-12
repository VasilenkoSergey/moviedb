package io.vasilenko.moviedb.data.repository

import io.vasilenko.moviedb.data.network.NetworkProvider
import io.vasilenko.moviedb.data.network.dto.MoviesResponseDto
import retrofit2.Call

object UpcomingMoviesRepository {

    fun getAll(): Call<MoviesResponseDto> = NetworkProvider.moviesApi().getUpcomingMovies()
}
