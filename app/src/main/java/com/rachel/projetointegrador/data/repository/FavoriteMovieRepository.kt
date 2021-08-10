package com.rachel.projetointegrador.data.repository

import com.rachel.projetointegrador.data.model.Movie

class FavoriteMovieRepository {

    fun addFavorite(movie: Movie) {
        movie.isFavorite = true
        favorites.putIfAbsent(movie.id, movie)
    }

    fun removeFavorite(movieId : Int) {
        favorites[movieId]?.isFavorite = false
        favorites.remove(movieId)
    }

    fun isFavorite(movieId: Int): Boolean {
        return favorites.contains(movieId)
    }

    fun listFavorites(): MutableList<Movie> {
        return ArrayList(favorites.values)
    }

    private companion object {
        val favorites : MutableMap<Int, Movie> = mutableMapOf()
    }
}