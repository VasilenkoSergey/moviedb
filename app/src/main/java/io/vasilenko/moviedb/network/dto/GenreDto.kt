package io.vasilenko.moviedb.network.dto

import com.google.gson.annotations.SerializedName

data class GenreDto(

    @SerializedName("name")
    val name: String?
)
