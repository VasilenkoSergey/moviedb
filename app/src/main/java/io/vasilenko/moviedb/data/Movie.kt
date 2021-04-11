package io.vasilenko.moviedb.data

data class Movie(
    var id: Int = 0,
    var title: String? = "",
    var voteAverage: Double = 0.0,
    var imagePath: String? = ""
) {
    val rating: Float
        get() = voteAverage.div(2).toFloat()
}
