package io.vasilenko.moviedb.data.repository

import io.reactivex.Single
import io.vasilenko.moviedb.data.mapper.PopularTvShowsMapper
import io.vasilenko.moviedb.data.TvShow
import io.vasilenko.moviedb.data.network.NetworkProvider

object PopularTvShowsRepository {

    fun getAll(): Single<List<TvShow>> {
        return NetworkProvider.tvShowsApi().getPopularTvShows().map {
            PopularTvShowsMapper.mapTvShowsDtosToModels(it.tvShows)
        }
    }
}
