package io.vasilenko.moviedb.data

data class MovieDetails(
    val id: Int = 0,
    val title: String? = "",
    val description: String? = "",
    val actors: List<Actor> = emptyList(),
    val studio: String? = "",
    val genre: String? = "",
    val year: String? = "",
    val voteAverage: Double = 0.0,
    val imageBackdropPath: String? = ""
) {
    val rating: Float
        get() = voteAverage.div(2).toFloat()
}
