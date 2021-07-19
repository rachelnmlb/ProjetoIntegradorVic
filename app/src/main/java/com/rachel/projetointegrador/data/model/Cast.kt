package com.rachel.projetointegrador.data.model

import com.google.gson.annotations.SerializedName

data class Cast (
    @SerializedName("profile_path") val profilePath: String,
    @SerializedName("name") val name: String,
    @SerializedName("character") val character: String
    )