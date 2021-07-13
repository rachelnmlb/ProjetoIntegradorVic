package com.rachel.projetointegrador.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rachel.projetointegrador.data.model.Genre
import com.rachel.projetointegrador.data.repository.GenreRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GenresViewModel : ViewModel() {

    val genresList = MutableLiveData<MutableList<Genre>>(mutableListOf())
    private val genreRepository = GenreRepository()

    fun loadGenres() {
        genreRepository.fetchGenresList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                it.message?.let { message -> Log.e("Error loading generes", message) }
            }
            .subscribe {
                genresList.value = it.genres
            }
    }
}