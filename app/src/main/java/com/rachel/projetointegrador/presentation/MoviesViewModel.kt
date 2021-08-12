package com.rachel.projetointegrador.presentation

import android.util.Log
import androidx.lifecycle.LiveData
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

    private val _popularMovies = MutableLiveData<MutableList<Movie>>(mutableListOf())
    private val _favoriteMovies = MutableLiveData<MutableList<Movie>>(mutableListOf())
    private val _searchResults = MutableLiveData<MutableList<Movie>>(mutableListOf())
    private val _genresList = MutableLiveData<MutableList<Genre>>(mutableListOf())

    val popularMovies: LiveData<MutableList<Movie>> = _popularMovies
    val favoriteMovies: LiveData<MutableList<Movie>> = _favoriteMovies
    val searchResults = _searchResults
    val genresList: LiveData<MutableList<Genre>> = _genresList

    private val genreRepository = GenreRepository()
    private val movieRepository = MovieRepository()
    private val favoriteMovieRepository = FavoriteMovieRepository()

    private var lastSearchQuery: String = ""

    fun loadPopularMovies(): Disposable {
        return movieRepository.fetchMoviesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _popularMovies.value = checkFavorites(it.results)
            }, {
                it.message?.let { message -> Log.e("Error loading movies", message) }
            })
    }

    fun loadMoviesByGenre(genreIds: List<Int>): Disposable {
        return movieRepository.fetchMovieByGenre(genreIds)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _popularMovies.value = checkFavorites(it.results)
            }, {
                it.message?.let { message -> Log.e("Error loading movies", message) }
            })
    }

    fun loadFavoriteMovies() {
        val favorites = favoriteMovieRepository.listFavorites()
        _favoriteMovies.value = favorites
    }

    fun loadFavoritesByGenre(genreIds: List<Int>) {
        val favorites = favoriteMovieRepository.listFavorites()
            .filter { movie -> movie.genreIds.containsAll(genreIds) }
            .toMutableList()

        _favoriteMovies.value = favorites
    }

    fun repeatLastSearch(): Disposable {
        return searchMovies(lastSearchQuery)
    }

    fun searchMovies(query: String): Disposable {
        lastSearchQuery = query
        return movieRepository.searchMovies(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _searchResults.value = checkFavorites(it.results)
            }, {
                it.message?.let { message -> Log.e("Error loading movies", message) }
            })
    }

    fun filterSearchResultsByGenre(genreIds: List<Int>) {
        val moviesByGenre = _searchResults.value
            ?.filter { movie -> movie.genreIds.containsAll(genreIds) }
            ?.toMutableList()

        _searchResults.value = moviesByGenre
    }

    fun loadGenres(): Disposable {
        return genreRepository.fetchGenresList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _genresList.value = it.genres
            }, {
                it.message?.let { message -> Log.e("Error loading generes", message) }
            })
    }

    fun addFavorite(movie: Movie) {
        favoriteMovieRepository.addFavorite(movie)
        notifyChanges()
    }

    fun removeFavorite(movie: Movie) {
        favoriteMovieRepository.removeFavorite(movie.id)
        notifyChanges()
    }

    fun notifyChanges() {
        _popularMovies.value = popularMovies.value?.let { checkFavorites(it) }
        _favoriteMovies.value = favoriteMovieRepository.listFavorites()
    }

    private fun checkFavorites(movies: MutableList<Movie>): MutableList<Movie> {
        return movies.map {
            it.isFavorite = favoriteMovieRepository.isFavorite(it.id)
            it
        }.toMutableList()
    }
}