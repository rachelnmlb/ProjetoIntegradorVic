package com.rachel.projetointegrador.data.model

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.bind.DateTypeAdapter
import java.util.*

data class MovieDetail(
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("release_date") val releaseDate: Date? = null,
    @SerializedName("runtime") val duration: Int? = null,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("overview") val overview: String? = null,

    )
