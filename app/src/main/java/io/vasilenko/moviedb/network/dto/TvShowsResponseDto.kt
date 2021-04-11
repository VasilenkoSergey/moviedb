package io.vasilenko.moviedb.network.dto

import com.google.gson.annotations.SerializedName

data class TvShowsResponseDto(

    @SerializedName("results")
    var tvShows: List<TvShowDto>
)
