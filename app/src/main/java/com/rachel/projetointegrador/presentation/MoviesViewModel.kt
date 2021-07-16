package com.rachel.projetointegrador.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rachel.projetointegrador.data.model.Genre
import com.rachel.projetointegrador.data.model.Movie
import com.rachel.projetointegrador.data.model.MoviesList
import com.rachel.projetointegrador.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesViewModel : ViewModel() {

    val movieRepository = MovieRepository()
    val popularMovieList = MutableLiveData<MutableList<Movie>>(mutableListOf())

    fun loadPopularMovies() {
        movieRepository.fetchMoviesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                it.message?.let { message -> Log.e("Error loading generes", message) }
            }
            .subscribe {
                popularMovieList.value = it.results
            }
    }
}