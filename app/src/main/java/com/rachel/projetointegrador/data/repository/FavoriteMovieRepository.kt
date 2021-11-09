package com.rachel.projetointegrador.data.repository

import android.content.Context
import com.rachel.projetointegrador.data.database.FavoriteMovieDatabase
import com.rachel.projetointegrador.data.model.entity.FavoriteMovie
import com.rachel.projetointegrador.data.model.Movie
import com.rachel.projetointegrador.data.model.entity.FavoriteMovieWithGenre
import com.rachel.projetointegrador.data.model.entity.Genre
import io.reactivex.Completable
import io.reactivex.Single

class FavoriteMovieRepository(context: Context) {

    private val favoriteMovieDAO = FavoriteMovieDatabase.getDatabase(context).favoriteMovieDao()

    fun addFavorite(movie: Movie): Completable {

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
        return favoriteMovieDAO.add(favorite, genres)
    }

    fun removeFavorite(movie: Movie): Completable {
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
        return favoriteMovieDAO.remove(favorite, genres)
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