package io.vasilenko.moviedb.data

data class TvShow(
    var id: Int = 0,
    var name: String? = "",
    var voteAverage: Double = 0.0,
    var imagePath: String? = ""
) {
    val rating: Float
        get() = voteAverage.div(2).toFloat()
}
