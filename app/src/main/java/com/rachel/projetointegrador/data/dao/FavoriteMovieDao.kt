package com.rachel.projetointegrador.data.dao

import androidx.room.*
import com.rachel.projetointegrador.data.model.FavoriteMovie

@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavoriteMovie(movie: FavoriteMovie)

    @Delete
    fun removeFavorite(favoriteMovie: FavoriteMovie)

    @Query("SELECT * FROM FavoritesMovies")
    fun listFavoriteMovies(): List<FavoriteMovie>

    @Query("SELECT * FROM FavoritesMovies WHERE id = :id")
    fun loadById(id: Int): List<FavoriteMovie>
}