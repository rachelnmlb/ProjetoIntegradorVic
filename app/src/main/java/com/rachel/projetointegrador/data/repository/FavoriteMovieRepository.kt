package com.rachel.projetointegrador.data.repository

import android.content.Context
import com.rachel.projetointegrador.data.database.FavoriteMovieDatabase
import com.rachel.projetointegrador.data.model.entity.FavoriteMovie
import com.rachel.projetointegrador.data.model.Movie
import com.rachel.projetointegrador.data.model.entity.FavoriteMovieWithGenre
import com.rachel.projetointegrador.data.model.entity.Genre
import io.reactivex.Single

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

    fun removeFavorite(movie: Movie) {
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
        favoriteMovieDAO.removeGenre(genres)
        favoriteMovieDAO.removeFavorite(favorite)
    }

    fun listFavorites(): Single<MutableList<Movie>> {
        return favoriteMovieDAO.listFavoriteMovies()
            .map {
                it.map { f ->
                    Movie(
                        f.favoriteMovie.id,
                        f.favoriteMovie.title,
                        f.favoriteMovie.posterPath,
                        f.genres.map { g -> g.id },
                        f.favoriteMovie.voteAverage,
                        true
                    )
                }.toMutableList()
            }

    }
}