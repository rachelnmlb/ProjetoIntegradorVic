package com.rachel.projetointegrador.data.model

import com.google.gson.annotations.SerializedName
import java.net.URI

data class Movie(
    @SerializedName("id") val id : Int? = null,
    @SerializedName("title") val title : String? = null,
    @SerializedName("poster_path") val posterPath : String? = null,
    @SerializedName("vote_average") val voteAverage : Float
    )