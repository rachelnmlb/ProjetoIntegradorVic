package com.rachel.projetointegrador.data.repository

import com.rachel.projetointegrador.data.model.Movie
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteMovieRepository {

    fun addFavorite(movie: Movie): Completable

    fun removeFavorite(movie: Movie): Completable

    fun listFavorites(): Single<MutableList<Movie>>
}