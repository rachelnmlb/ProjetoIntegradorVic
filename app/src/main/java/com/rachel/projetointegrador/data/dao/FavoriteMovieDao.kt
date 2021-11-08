package com.rachel.projetointegrador.data.dao

import androidx.room.*
import com.rachel.projetointegrador.data.model.entity.FavoriteMovie
import com.rachel.projetointegrador.data.model.entity.FavoriteMovieWithGenre
import com.rachel.projetointegrador.data.model.entity.Genre
import java.util.*

@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavoriteMovie(movie: FavoriteMovie)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addGenre(genres: List<Genre>)

    @Delete
    fun removeFavorite(favoriteMovie: FavoriteMovie)

    @Delete
    fun removeGenre(genres: List<Genre>)

    @Query("SELECT * FROM FavoritesMovies")
    fun listFavoriteMovies(): List<FavoriteMovieWithGenre>

    @Query("SELECT * FROM FavoritesMovies WHERE id = :id")
    fun loadById(id: Int): Optional<FavoriteMovieWithGenre>
}