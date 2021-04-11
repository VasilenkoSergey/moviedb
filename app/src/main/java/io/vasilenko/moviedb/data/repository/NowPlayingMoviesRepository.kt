package io.vasilenko.moviedb.data.repository

import io.vasilenko.moviedb.data.network.NetworkProvider
import io.vasilenko.moviedb.data.network.dto.MoviesResponseDto
import retrofit2.Call

object NowPlayingMoviesRepository {

    fun getAll(): Call<MoviesResponseDto> =
        NetworkProvider.moviesApi().getNowPlayingMovies()
}
