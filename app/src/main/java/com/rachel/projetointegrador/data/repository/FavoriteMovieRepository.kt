package com.rachel.projetointegrador.data.repository

import android.content.Context
import com.rachel.projetointegrador.data.database.FavoriteMovieDatabase
import com.rachel.projetointegrador.data.model.FavoriteMovie
import com.rachel.projetointegrador.data.model.Movie

class FavoriteMovieRepository(context: Context) {

    private val favoriteMovieDAO = FavoriteMovieDatabase.getDatabase(context).favoriteMovieDao()

    fun addFavorite(movie: Movie) {

        movie.isFavorite = true

        val favorite = FavoriteMovie(
            movie.id,
            movie.title,
            movie.posterPath,
            movie.genreIds.joinToString(","),
            movie.voteAverage)

        favoriteMovieDAO.addFavoriteMovie(favorite)
    }

    fun removeFavorite(movieId : Int) {
        val favorite = favoriteMovieDAO.loadById(movieId)
        if (favorite.isPresent) {
            favoriteMovieDAO.removeFavorite(favorite.get())
        }
    }

    fun isFavorite(movieId: Int): Boolean {
        return favoriteMovieDAO.loadById(movieId).isPresent
    }

    fun listFavorites(): MutableList<Movie> {
        return favoriteMovieDAO.listFavoriteMovies()
            .map { f ->
                Movie(
                    f.id,
                    f.title,
                    f.posterPath,
                    f.genreIds.split(",").map{it.toInt()},
                    f.voteAverage,
                    true)
            }.toMutableList()
    }
}