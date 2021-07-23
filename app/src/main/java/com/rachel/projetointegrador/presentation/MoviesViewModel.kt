package com.rachel.projetointegrador.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rachel.projetointegrador.data.model.Movie

abstract class MoviesViewModel : ViewModel() {

    val moviesList = MutableLiveData<MutableList<Movie>>(mutableListOf())

    abstract fun loadMovies()
    abstract fun loadMoviesByGenre(genreIds: List<Int>)

    fun updateValue(value: MutableList<Movie>) {
        moviesList.value = value
    }
}