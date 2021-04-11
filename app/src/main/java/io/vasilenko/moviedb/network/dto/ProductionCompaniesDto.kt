package io.vasilenko.moviedb.network.dto

import com.google.gson.annotations.SerializedName

data class ProductionCompaniesDto(

    @SerializedName("name")
    val name: String?
)
