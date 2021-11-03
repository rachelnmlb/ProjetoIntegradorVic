package com.rachel.projetointegrador.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoritesMovies")
class FavoriteMovie (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="Id")
    val id: Int,

    @ColumnInfo(name="title")
    val title: String? = null,

    @ColumnInfo(name="posterPath")
    val posterPath : String? = null,

    @ColumnInfo(name="genreIds")
    val genreIds: String = "",

    @ColumnInfo(name="voteAverage")
    val voteAverage : Float
)