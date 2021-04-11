package io.vasilenko.moviedb.data.local

import io.vasilenko.moviedb.data.Movie

object MockRepository {

    fun getWatchlistMovies(): List<Movie> {

        val moviesList = mutableListOf<Movie>()
        for (x in 0..10) {
            val movie = Movie(
                title = "Spider-Man $x",
                voteAverage = 10.0 - x,
                imagePath = "https://www.kinopoisk.ru/images/film_big/1143242.jpg"
            )
            moviesList.add(movie)
        }

        return moviesList
    }
}
