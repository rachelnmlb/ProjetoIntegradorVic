package com.rachel.projetointegrador.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rachel.projetointegrador.data.model.MovieDetail
import com.rachel.projetointegrador.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailViewModel: ViewModel() {
    private val detailRepository = MovieRepository()
    val movieDetail = MutableLiveData<MovieDetail>()

    fun loadMovieDetail(movieId: Int): Disposable{
        return detailRepository.fetchMovieDetail(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                it.message?.let { message -> Log.e("Error loading movies", message) }
            }
            .subscribe {
                movieDetail.value = it
            }
    }
}