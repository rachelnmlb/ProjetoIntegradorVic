package com.rachel.projetointegrador.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoritesMovies")
data class FavoriteMovie (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="id")
    val id: Int,

    @ColumnInfo(name="title")
    val title: String? = null,

    @ColumnInfo(name="posterPath")
    val posterPath : String? = null,

    @ColumnInfo(name="voteAverage")
    val voteAverage : Float
)