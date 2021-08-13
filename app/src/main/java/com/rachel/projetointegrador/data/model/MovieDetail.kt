package com.rachel.projetointegrador.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("id") val id : Int,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("runtime") val runtime: Int? = null,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("release_dates") val releaseDates: ReleaseDateResultList?,
    var isFavorite: Boolean = false
    ) {

    fun releaseYear(): String {
        val releaseYear =  releaseDate?.let {
            if (it.length < 4) ""
            else it.substring(0, 4)
        }?: ""

        return releaseYear.toIntOrNull()?.toString() ?: ""
    }

    fun runtimeString(): String {

        val duration = runtime ?: 0
        val hourPart: Int
        var remainingTime = duration
        var durationText = ""

        if (duration == 0) return "0h"

        if (duration >= 60) {
            hourPart = duration / 60
            durationText = "${hourPart}h "
            remainingTime -= hourPart * 60
        }

        if (remainingTime > 0) {
            durationText += "${remainingTime.toString().padStart(2, '0')}min"
        }

        return durationText.trim()
    }

    fun parentalGuidance(): String {

        val regions = listOf("BR", "US")

        return releaseDates?.results
            ?.filter { regions.contains(it.iso_3166_1) }
            ?.flatMap { it.releaseDates!! }
            ?.firstOrNull {  !it.certification.isNullOrBlank() }
            ?.certification ?: ""
    }
}
