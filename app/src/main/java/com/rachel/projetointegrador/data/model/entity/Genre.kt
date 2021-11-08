package com.rachel.projetointegrador.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Genre", primaryKeys = ["genreId", "movieId"])
data class Genre (

    @ColumnInfo(name = "genreId")
    val id : Int,

    @ColumnInfo(name = "movieId")
    val movieId : Int

    )