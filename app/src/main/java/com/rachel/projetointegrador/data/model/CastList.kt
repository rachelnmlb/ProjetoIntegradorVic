package com.rachel.projetointegrador.data.model

import com.google.gson.annotations.SerializedName

data class CastList(
    @SerializedName("cast") val cast: MutableList<Cast>
    )
