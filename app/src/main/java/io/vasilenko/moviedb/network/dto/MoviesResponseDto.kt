package io.vasilenko.moviedb.network.dto

import com.google.gson.annotations.SerializedName

data class MoviesResponseDto(

    @SerializedName("results")
    var movies: List<MovieDto>
)
