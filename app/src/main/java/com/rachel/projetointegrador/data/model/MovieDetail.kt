package com.rachel.projetointegrador.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("id") val id : Int,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("runtime") val duration: Int? = null,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("overview") val overview: String? = null,
    var isFavorite: Boolean = false
    ) {

    fun getReleaseYear(): String {
        return releaseDate?.substring(0, 4) ?: ""
    }
}
