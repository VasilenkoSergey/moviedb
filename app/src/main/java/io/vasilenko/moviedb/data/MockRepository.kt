package io.vasilenko.moviedb.data

object MockRepository {

    fun getRecommendedMovies(): List<Movie> {

        val moviesList = mutableListOf<Movie>()
        for (x in 0..10) {
            val movie = Movie(
                title = "Spider-Man $x",
                voteAverage = 10.0 - x,
                imagePath = "https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg"
            )
            moviesList.add(movie)
        }

        return moviesList
    }

    fun getNewMovies(): List<Movie> {

        val moviesList = mutableListOf<Movie>()
        for (x in 0..10) {
            val movie = Movie(
                title = "Spider-Man $x",
                voteAverage = 10.0 - x,
                imagePath = "https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg"
            )
            moviesList.add(movie)
        }

        return moviesList
    }

    fun getTvShows(): List<Movie> {

        val moviesList = mutableListOf<Movie>()
        val movieWithLongTitle = Movie(
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam",
            voteAverage = 10.0 - 0,
            imagePath = "https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg"
        )
        moviesList.add(movieWithLongTitle)
        for (x in 1..10) {
            val movie = Movie(
                title = "Spider-Man $x",
                voteAverage = 10.0 - x,
                imagePath = "https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg"
            )
            moviesList.add(movie)
        }

        return moviesList
    }

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

    fun getMovieById(stubId: Long = 0): MovieDetails {
        return MovieDetails(
            title = "Aquaman",
            description = "In 1985 Maine, lighthouse keeper Thomas Curry rescues Atlanna, the queen of the underwater nation of Atlantis, during a storm. They eventually fall in love and have a son named Arthur, who is born with the power to communicate with marine lifeforms. ",
            actors = listOf(
                Actor(
                    name = "Jason Momoa",
                    imagePath = "https://image.tmdb.org/t/p/w90_and_h90_face/6dEFBpZH8C8OijsynkSajQT99Pb.jpg"
                ),
                Actor(
                    name = "Amber Heard",
                    imagePath = "https://image.tmdb.org/t/p/w90_and_h90_face/1cb5mGzGB6Sj2JPkWt9W16B19Bf.jpg"
                ),
                Actor(
                    name = "Patric Wilson",
                    imagePath = "https://image.tmdb.org/t/p/w90_and_h90_face/tc1ezEfIY8BhCy85svOUDtpBFPt.jpg"
                ),
                Actor(
                    name = "Nickole Kidman",
                    imagePath = "https://image.tmdb.org/t/p/w90_and_h90_face/6YoLMiw83OGmz58UK2i4MAKAMav.jpg"
                )
            ),
            studio = "Warner Bros.",
            genre = "Action, Adventure, Fantasy ",
            year = "2018",
            voteAverage = 10.0,
            imageBackdropPath = "https://image.tmdb.org/t/p/w780/5iidzov8DrsSyZdefeo7jBLDNUW.jpg"
        )
    }
}
