package io.vasilenko.moviedb.data.mapper

import io.vasilenko.moviedb.BuildConfig
import io.vasilenko.moviedb.data.Actor
import io.vasilenko.moviedb.data.MovieDetails
import io.vasilenko.moviedb.data.network.dto.CastDto
import io.vasilenko.moviedb.data.network.dto.MovieDto
import io.vasilenko.moviedb.ui.moviedetails.ActorItem

object MovieDetailsMapper {

    fun mapMovieDtoToModel(movie: MovieDto): MovieDetails {
        return MovieDetails(
            id = movie.id,
            title = movie.title,
            description = movie.overview,
            voteAverage = movie.rating,
            imageBackdropPath = movie.backdropPath?.let { path ->
                BuildConfig.THE_MOVIE_DATABASE_BACKDROP_BASE_URL + path
            },
            studio = movie.productionCompanies?.joinToString { company ->
                "${company.name}"
            },
            genre = movie.genres?.joinToString { genre ->
                "${genre.name}"
            },
            year = movie.releaseDate
        )
    }

    fun mapActorsDtoToModel(casts: List<CastDto>?): List<Actor>? {
        return casts?.map {
            Actor(
                name = it.name,
                imagePath = it.profilePath?.let { path ->
                    BuildConfig.THE_MOVIE_DATABASE_PROFILE_BASE_URL + path
                }
            )
        }
    }

    fun mapActorModelsToItems(actors: List<Actor>) = actors.map { ActorItem(it) }
}
