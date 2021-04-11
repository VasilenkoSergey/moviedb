package io.vasilenko.moviedb.data.network.dto

import com.google.gson.annotations.SerializedName
import io.vasilenko.moviedb.data.network.dto.CastDto

data class MovieCreditsDto(

    @SerializedName("cast")
    val cast: List<CastDto>?
)
