package io.vasilenko.moviedb.data.network.dto

import com.google.gson.annotations.SerializedName
import io.vasilenko.moviedb.data.network.dto.TvShowDto

data class TvShowsResponseDto(

    @SerializedName("results")
    var tvShows: List<TvShowDto>
)
