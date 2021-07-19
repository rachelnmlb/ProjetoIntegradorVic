package com.rachel.projetointegrador.data.model

import com.google.gson.annotations.SerializedName

data class MoviesList(
    @SerializedName("results")val results: MutableList<Movie>)
