package io.vasilenko.moviedb.data

import io.vasilenko.moviedb.network.MovieApiClient
import io.vasilenko.moviedb.network.dto.MovieCreditsDto
import io.vasilenko.moviedb.network.dto.MovieDto
import retrofit2.Call

object MovieDetailsRepository {

    fun getById(id: Int): Call<MovieDto> = MovieApiClient.moviesApi().getMovie(id)

    fun getCreditsById(id: Int): Call<MovieCreditsDto> =
        MovieApiClient.moviesApi().getMovieCredits(id)
}
