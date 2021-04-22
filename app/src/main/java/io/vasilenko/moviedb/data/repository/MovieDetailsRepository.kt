package io.vasilenko.moviedb.data.repository

import io.reactivex.Single
import io.vasilenko.moviedb.data.Actor
import io.vasilenko.moviedb.data.mapper.MovieDetailsMapper
import io.vasilenko.moviedb.data.network.NetworkProvider
import io.vasilenko.moviedb.data.network.dto.MovieDto

object MovieDetailsRepository {

    fun getById(id: Int): Single<MovieDto> {
        return NetworkProvider.moviesApi().getMovie(id)
    }

    fun getActorsByMovieId(id: Int): Single<List<Actor>> {
        return NetworkProvider.moviesApi().getMovieCredits(id).map {
            MovieDetailsMapper.mapActorsDtoToModel(it.cast)
        }
    }
}
