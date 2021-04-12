package io.vasilenko.moviedb.data.network.dto

import com.google.gson.annotations.SerializedName

data class MovieCreditsDto(

    @SerializedName("cast")
    val cast: List<CastDto>?
)
