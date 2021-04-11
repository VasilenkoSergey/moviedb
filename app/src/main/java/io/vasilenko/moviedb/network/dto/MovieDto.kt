package io.vasilenko.moviedb.network.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("vote_average")
    val rating: Double,

    @SerializedName("poster_path")
    var posterPath: String? = null
)
