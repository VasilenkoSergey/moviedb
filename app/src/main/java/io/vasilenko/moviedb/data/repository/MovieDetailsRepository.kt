package io.vasilenko.moviedb.data.repository

import io.vasilenko.moviedb.data.network.NetworkProvider
import io.vasilenko.moviedb.data.network.dto.MovieCreditsDto
import io.vasilenko.moviedb.data.network.dto.MovieDto
import retrofit2.Call

object MovieDetailsRepository {

    fun getById(id: Int): Call<MovieDto> = NetworkProvider.moviesApi().getMovie(id)

    fun getCreditsById(id: Int): Call<MovieCreditsDto> =
        NetworkProvider.moviesApi().getMovieCredits(id)
}
