package com.rachel.projetointegrador.data.repository

import com.rachel.projetointegrador.data.model.Movie

object FavoriteMovieRepository {
    private val favorites : MutableMap<Int, Movie> = mutableMapOf()

    fun addFavorite(movie: Movie) {
        favorites.putIfAbsent(movie.id, movie)
    }

    fun removeFavorite(movieId : Int) {
        favorites.remove(movieId)
    }

    fun isFavorite(movieId: Int): Boolean {
        return favorites.contains(movieId)
    }

    fun listFavorites(): MutableList<Movie> {
        return ArrayList(favorites.values)
    }
}