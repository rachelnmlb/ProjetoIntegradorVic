package com.rachel.projetointegrador.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rachel.projetointegrador.data.model.Movie
import com.rachel.projetointegrador.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MoviesViewModel : ViewModel() {

    private val movieRepository = MovieRepository()
    val moviesList = MutableLiveData<MutableList<Movie>>(mutableListOf())

    fun loadPopularMovies(): Disposable {
        return movieRepository.fetchMoviesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                it.message?.let { message -> Log.e("Error loading movies", message) }
            }
            .subscribe {
                moviesList.value = it.results
            }
    }

    fun loadMoviesByGenre(genreIds: String): Disposable {
        return movieRepository.fetchMovieByGenre(genreIds)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                it.message?.let { message -> Log.e("Error loading movies", message) }
            }
            .subscribe {
                moviesList.value = it.results
            }
    }
}