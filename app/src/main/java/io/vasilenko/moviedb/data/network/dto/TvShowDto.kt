package io.vasilenko.moviedb.data.network.dto

import com.google.gson.annotations.SerializedName

data class TvShowDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("vote_average")
    val rating: Double,

    @SerializedName("poster_path")
    var posterPath: String?
)
