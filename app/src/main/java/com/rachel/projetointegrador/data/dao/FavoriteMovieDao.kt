package com.rachel.projetointegrador.data.dao

import androidx.room.*
import com.rachel.projetointegrador.data.model.entity.FavoriteMovie
import com.rachel.projetointegrador.data.model.entity.FavoriteMovieWithGenre
import com.rachel.projetointegrador.data.model.entity.Genre
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*

@Dao
interface FavoriteMovieDao {

    fun add(movie: FavoriteMovie, genres: List<Genre>): Completable {
        return Completable.fromAction { addFavoriteMovie(movie, genres) }
    }

    fun remove(movie: FavoriteMovie, genres: List<Genre>): Completable {
        return Completable.fromAction { removeFavorite(movie, genres) }
    }

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteMovie(movie: FavoriteMovie, genres: List<Genre>)

    @Transaction
    @Delete
    fun removeFavorite(favoriteMovie: FavoriteMovie, genres: List<Genre>)

    @Transaction
    @Query("SELECT * FROM FavoritesMovies")
    fun listFavoriteMovies():Single<List<FavoriteMovieWithGenre>>

    @Query("SELECT * FROM FavoritesMovies WHERE id = :id")
    fun loadById(id: Int): Maybe<FavoriteMovieWithGenre>
}