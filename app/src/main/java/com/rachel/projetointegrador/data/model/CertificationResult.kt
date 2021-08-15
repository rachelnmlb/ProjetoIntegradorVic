package com.rachel.projetointegrador.data.model

import com.google.gson.annotations.SerializedName

data class CertificationResult(
    @SerializedName("iso_3166_1") val iso_3166_1: String?,
    @SerializedName("release_dates") val certifications: List<Certification> = listOf(),
)