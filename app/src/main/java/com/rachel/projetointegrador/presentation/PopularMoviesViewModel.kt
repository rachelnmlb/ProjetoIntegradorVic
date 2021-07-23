package com.rachel.projetointegrador.presentation

import android.annotation.SuppressLint
import android.util.Log
import com.rachel.projetointegrador.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PopularMoviesViewModel : MoviesViewModel() {

    private val movieRepository = MovieRepository()

    override fun loadMovies() {
         movieRepository.fetchMoviesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                it.message?.let { message -> Log.e("Error loading movies", message) }
            }
            .subscribe {
                updateValue(it.results)
            }
    }

    override fun loadMoviesByGenre(genreIds: List<Int>) {
         movieRepository.fetchMovieByGenre(genreIds)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                it.message?.let { message -> Log.e("Error loading movies", message) }
            }
            .subscribe {
                updateValue(it.results)
            }
    }
}