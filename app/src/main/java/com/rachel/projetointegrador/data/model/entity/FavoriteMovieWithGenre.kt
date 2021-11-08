package com.rachel.projetointegrador.data.model.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FavoriteMovieWithGenre (
    @Embedded val favoriteMovie : FavoriteMovie,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val genres: List<Genre>
)