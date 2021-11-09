package com.rachel.projetointegrador.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rachel.projetointegrador.data.RequestStatus
import com.rachel.projetointegrador.data.model.Genre
import com.rachel.projetointegrador.data.model.Movie
import com.rachel.projetointegrador.data.repository.FavoriteMovieRepository
import com.rachel.projetointegrador.data.repository.GenreRepository
import com.rachel.projetointegrador.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteMovieRepository = FavoriteMovieRepository(application.applicationContext)

    private val _popularMovies = MutableLiveData<MutableList<Movie>>(mutableListOf())
    private val _favoriteMovies = MutableLiveData<MutableList<Movie>>(mutableListOf())
    private val _searchResults = MutableLiveData<MutableList<Movie>>(mutableListOf())
    private val _genresList = MutableLiveData<MutableList<Genre>>(mutableListOf())
    private val _requestStatus = MutableLiveData<RequestStatus>()

    val popularMovies: LiveData<MutableList<Movie>> = _popularMovies
    val favoriteMovies: LiveData<MutableList<Movie>> = _favoriteMovies
    val searchResults: LiveData<MutableList<Movie>> = _searchResults
    val genresList: LiveData<MutableList<Genre>> = _genresList
    val requestStatus: LiveData<RequestStatus> = _requestStatus

    private var lastSearchQuery: String = ""

    fun loadPopularMovies(): Disposable {
        return MovieRepository.fetchMoviesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _popularMovies.value = it.results
                checkFavorites(_popularMovies)
            }, {
                handleError("Error loading movies", it)
            })
    }

    fun loadMoviesByGenre(genreIds: List<Int>): Disposable {
        return MovieRepository.fetchMovieByGenre(genreIds)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _popularMovies.value = it.results
                checkFavorites(_popularMovies)
            }, {
                handleError("Error loading movies", it)
            })
    }

    fun loadFavoriteMovies(): Disposable {
        return favoriteMovieRepository.listFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _favoriteMovies.value = it
            }, {
                handleError("Error loading movies", it)
            })
    }

    fun loadFavoritesByGenre(genreIds: List<Int>): Disposable {
        return favoriteMovieRepository.listFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _favoriteMovies.value = it.filter {
                        movie -> movie.genreIds.containsAll(genreIds)
                }.toMutableList()
            }, {
                handleError("Error loading movies", it)
            })
    }

    fun repeatLastSearch(): Disposable {
        return searchMovies(lastSearchQuery)
    }

    fun searchMovies(query: String): Disposable {
        lastSearchQuery = query
        return MovieRepository.searchMovies(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _searchResults.value = it.results
                checkFavorites(_searchResults)
            }, {
                handleError("Error loading movies", it)
            })
    }

    fun filterSearchResultsByGenre(genreIds: List<Int>) {
        val moviesByGenre = _searchResults.value
            ?.filter { movie -> movie.genreIds.containsAll(genreIds) }
            ?.toMutableList()

        _searchResults.value = moviesByGenre
    }

    fun loadGenres(): Disposable {
        return GenreRepository.fetchGenresList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _genresList.value = it.genres
            }, {
                handleError("Error loading genres", it)
            })
    }

    fun addFavorite(movie: Movie): Disposable {
        return favoriteMovieRepository.addFavorite(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                notifyChanges()
            },{
                handleError("Error loading genres", it)
            })
    }

    fun removeFavorite(movie: Movie): Disposable {
        return favoriteMovieRepository.removeFavorite(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                notifyChanges()
            },{
                handleError("Error loading genres", it)
            })
    }

    fun notifyChanges() {
        //TODO: otimizar a checagem de favoritos. Muitas pesquisas no banco de dados
        checkFavorites(_popularMovies)
        checkFavorites(_searchResults)
        loadFavoriteMovies()
    }

    private fun handleError(tag: String, error: Throwable) {
        _requestStatus.value = RequestStatus.ERROR
        error.message?.let { message -> Log.e(tag, message) }
    }

    private fun checkFavorites(movieData: MutableLiveData<MutableList<Movie>>): Disposable {
        return favoriteMovieRepository.listFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { favorites ->
                    movieData.value = movieData.value?.map {
                        it.isFavorite = favorites.map { f -> f.id }.contains(it.id)
                        it
                    }?.toMutableList()
                },
                {handleError("Error loading favorites", it)})
    }
}