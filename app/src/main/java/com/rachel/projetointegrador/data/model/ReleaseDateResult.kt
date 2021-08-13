package com.rachel.projetointegrador.data.model

import com.google.gson.annotations.SerializedName

data class ReleaseDateResult(
    @SerializedName("iso_3166_1") val iso_3166_1: String?,
    @SerializedName("release_dates") val releaseDates: List<ReleaseDate>?,
)