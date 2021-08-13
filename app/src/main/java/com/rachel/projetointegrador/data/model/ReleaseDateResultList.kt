package com.rachel.projetointegrador.data.model

import com.google.gson.annotations.SerializedName

data class ReleaseDateResultList(
    @SerializedName("results")val results: MutableList<ReleaseDateResult>?
)