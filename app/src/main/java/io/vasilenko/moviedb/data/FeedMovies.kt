package io.vasilenko.moviedb.data

import io.vasilenko.moviedb.data.network.dto.MovieDto

data class FeedMovies(
    val nowPlayingMovies: List<MovieDto>,
    val upcomingPlayingMovies: List<MovieDto>,
    val popularMovies: List<MovieDto>
)
