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

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteMovie(movie: FavoriteMovie, genres: List<Genre>): Single<List<Long>>

    @Transaction
    @Delete
    fun removeFavorite(favoriteMovie: FavoriteMovie, genres: List<Genre>): Single<List<Long>>

    @Query("SELECT * FROM FavoritesMovies")
    fun listFavoriteMovies():Single<List<FavoriteMovieWithGenre>>

    @Query("SELECT * FROM FavoritesMovies WHERE id = :id")
    fun loadById(id: Int): Maybe<FavoriteMovieWithGenre>
}