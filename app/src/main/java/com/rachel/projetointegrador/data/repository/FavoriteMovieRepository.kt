package com.rachel.projetointegrador.data.repository

import android.content.Context
import com.rachel.projetointegrador.data.database.FavoriteMovieDatabase
import com.rachel.projetointegrador.data.model.entity.FavoriteMovie
import com.rachel.projetointegrador.data.model.Movie
import com.rachel.projetointegrador.data.model.entity.FavoriteMovieWithGenre
import com.rachel.projetointegrador.data.model.entity.Genre

class FavoriteMovieRepository(context: Context) {

    private val favoriteMovieDAO = FavoriteMovieDatabase.getDatabase(context).favoriteMovieDao()

    fun addFavorite(movie: Movie) {

        movie.isFavorite = true

        val favorite = FavoriteMovie(
            movie.id,
            movie.title,
            movie.posterPath,
            movie.voteAverage)

        val genres = movie.genreIds.map {
            Genre(
                it,
                movie.id
            )
        }
        favoriteMovieDAO.addFavoriteMovie(favorite)
        favoriteMovieDAO.addGenre(genres)
    }

    fun removeFavorite(movieId : Int) {
        val favorite = favoriteMovieDAO.loadById(movieId)
        if (favorite.isPresent) {
            favoriteMovieDAO.removeGenre(favorite.get().genres)
            favoriteMovieDAO.removeFavorite(favorite.get().favoriteMovie)
        }
    }

    fun isFavorite(movieId: Int): Boolean {
        return favoriteMovieDAO.loadById(movieId).isPresent
    }

    fun listFavorites(): MutableList<Movie> {
        return favoriteMovieDAO.listFavoriteMovies()
            .map { f ->
                Movie(
                    f.favoriteMovie.id,
                    f.favoriteMovie.title,
                    f.favoriteMovie.posterPath,
                    f.genres.map { g -> g.id },
                    f.favoriteMovie.voteAverage,
                    true)
            }.toMutableList()
    }
}