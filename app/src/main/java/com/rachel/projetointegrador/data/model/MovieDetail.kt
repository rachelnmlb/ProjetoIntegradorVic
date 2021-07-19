package com.rachel.projetointegrador.data.model

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.bind.DateTypeAdapter
import java.util.*

data class MovieDetail(
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val rating: Int,
    @SerializedName("release_date") val releaseDate: Date,
    @SerializedName("runtime") val duration: Int,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("overview") val overview: String,

    )
