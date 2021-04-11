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
    var posterPath: String?,

    @SerializedName("overview")
    var overview: String?,

    @SerializedName("backdrop_path")
    var backdropPath: String?,

    @SerializedName("genres")
    var genres: List<GenreDto>?,

    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompaniesDto>?,

    @SerializedName("release_date")
    var releaseDate: String?
)
