package com.rachel.projetointegrador.data.model

import com.google.gson.annotations.SerializedName

data class GenresList(
    @SerializedName("genres") val genres: MutableList<Genre>)