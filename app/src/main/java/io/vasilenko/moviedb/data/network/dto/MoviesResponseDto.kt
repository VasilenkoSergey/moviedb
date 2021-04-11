package io.vasilenko.moviedb.data.network.dto

import com.google.gson.annotations.SerializedName
import io.vasilenko.moviedb.data.network.dto.MovieDto

data class MoviesResponseDto(

    @SerializedName("results")
    var movies: List<MovieDto>
)
