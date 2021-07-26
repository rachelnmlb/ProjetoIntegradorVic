package com.rachel.projetointegrador.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rachel.projetointegrador.data.model.Genre
import com.rachel.projetointegrador.data.model.Movie
import com.rachel.projetointegrador.data.repository.FavoriteMovieRepository
import com.rachel.projetointegrador.data.repository.GenreRepository
import com.rachel.projetointegrador.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MoviesViewModel : ViewModel() {

    val popularMovies = MutableLiveData<MutableList<Movie>>(mutableListOf())
    val favoriteMovies = MutableLiveData<MutableList<Movie>>(mutableListOf())
    val genresList = MutableLiveData<MutableList<Genre>>(mutableListOf())

    private val genreRepository = GenreRepository()
    private val movieRepository = MovieRepository()
    private val favoriteMovieRepository = FavoriteMovieRepository()

    fun loadPopularMovies(): Disposable {
        return movieRepository.fetchMoviesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                it.message?.let { message -> Log.e("Error loading movies", message) }
            }
            .subscribe {
                popularMovies.value = it.results
                checkFavorites()
            }
    }

    fun loadMoviesByGenre(genreIds: List<Int>): Disposable {
        return movieRepository.fetchMovieByGenre(genreIds)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                it.message?.let { message -> Log.e("Error loading movies", message) }
            }
            .subscribe {
                popularMovies.value = it.results
                checkFavorites()
            }
    }

    fun loadGenres(): Disposable {
        return genreRepository.fetchGenresList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                it.message?.let { message -> Log.e("Error loading generes", message) }
            }
            .subscribe {
                genresList.value = it.genres
            }
    }

    fun loadFavoriteMovies() {
        val favorites = favoriteMovieRepository.listFavorites()
        favoriteMovies.value = favorites
    }

    fun loadFavoritesByGenre(genreIds: List<Int>) {
        val favorites = favoriteMovieRepository.listFavorites()
            .filter { movie -> movie.genreIds.containsAll(genreIds) }
            .toMutableList()

        favoriteMovies.value = favorites
    }

    fun addFavorite(movie: Movie) {
        favoriteMovieRepository.addFavorite(movie)
        favoriteMovies.value = favoriteMovieRepository.listFavorites()
        checkFavorites()
    }

    fun removeFavorite(movieId: Int) {
        favoriteMovieRepository.removeFavorite(movieId)
        favoriteMovies.value = favoriteMovieRepository.listFavorites()
        checkFavorites()
    }

    private fun checkFavorites() {
        val favorites = popularMovies.value?.map {
            it.isFavorite = favoriteMovieRepository.isFavorite(it.id)
            it
        }
        popularMovies.value = favorites?.toMutableList()
    }
}