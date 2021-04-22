package io.vasilenko.moviedb.data.repository

import io.reactivex.Single
import io.vasilenko.moviedb.data.network.NetworkProvider
import io.vasilenko.moviedb.data.network.dto.MoviesResponseDto

object UpcomingMoviesRepository {

    fun getAll(): Single<MoviesResponseDto> = NetworkProvider.moviesApi().getUpcomingMovies()
}
