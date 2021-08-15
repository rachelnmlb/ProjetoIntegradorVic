package com.rachel.projetointegrador.data.model

import com.google.gson.annotations.SerializedName

data class CertificationResultsList(
    @SerializedName("results")val results: MutableList<CertificationResult>?
)