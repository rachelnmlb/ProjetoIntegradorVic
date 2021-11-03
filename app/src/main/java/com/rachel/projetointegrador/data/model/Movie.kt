package com.rachel.projetointegrador.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String? = null,
    @SerializedName("poster_path") val posterPath : String? = null,
    @SerializedName("genre_ids") val genreIds : List<Int>,
    @SerializedName("vote_average") val voteAverage : Float,
    var isFavorite : Boolean = false
    ) {

    fun voteAveragePercent(): String {
        val rating = (voteAverage * 10.0)
        return "${"%.0f".format(rating)}%"
    }
}