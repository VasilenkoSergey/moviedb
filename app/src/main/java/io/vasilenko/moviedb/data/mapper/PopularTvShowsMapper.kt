package io.vasilenko.moviedb.data.mapper

import io.vasilenko.moviedb.BuildConfig
import io.vasilenko.moviedb.data.TvShow
import io.vasilenko.moviedb.data.network.dto.TvShowDto
import io.vasilenko.moviedb.ui.tvshows.TvShowItem

object PopularTvShowsMapper {

    fun mapTvShowsDtosToModels(tvShows: List<TvShowDto>): List<TvShow> {
        return tvShows.map {
            TvShow(
                id = it.id,
                name = it.name,
                voteAverage = it.rating,
                imagePath = it.posterPath?.let { path ->
                    BuildConfig.THE_MOVIE_DATABASE_POSTER_BASE_URL + path
                }
            )
        }
    }

    fun mapTvShowsModelsToItems(tvShows: List<TvShow>): List<TvShowItem> {
        return tvShows.map { tvShow ->
            TvShowItem(
                tvShow
            )
        }
    }
}
