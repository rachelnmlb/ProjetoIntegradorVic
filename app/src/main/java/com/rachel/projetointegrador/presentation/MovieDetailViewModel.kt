package com.rachel.projetointegrador.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rachel.projetointegrador.data.RequestStatus
import com.rachel.projetointegrador.data.dao.FavoriteMovieDao
import com.rachel.projetointegrador.data.model.Movie
import com.rachel.projetointegrador.data.model.MovieDetail
import com.rachel.projetointegrador.data.repository.FavoriteMovieLocalRepository
import com.rachel.projetointegrador.data.repository.FavoriteMovieRepository
import com.rachel.projetointegrador.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailViewModel(private val favoriteMovieRepository: FavoriteMovieRepository): ViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetail>()
    private val _requestStatus = MutableLiveData<RequestStatus>()

    val movieDetail: LiveData<MovieDetail> = _movieDetail
    val requestStatus: LiveData<RequestStatus> = _requestStatus

    fun loadMovieDetail(movieId: Int): Disposable {

        return MovieRepository.fetchMovieDetail(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _movieDetail.value = it
                checkFavorites(_movieDetail)
            }, {
                _requestStatus.value = RequestStatus.ERROR
                it.message?.let { message -> Log.e("Error loading movie", message) }
            })
    }

    fun addFavorite() {
        _movieDetail.value?.let {
            val movie = Movie(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                genreIds = it.genres.map { genre -> genre.id },
                voteAverage = it.voteAverage
            )
            favoriteMovieRepository.addFavorite(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }

    fun removeFavorite() {
        _movieDetail.value?.let {
            val movie = Movie(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                genreIds = it.genres.map { genre -> genre.id },
                voteAverage = it.voteAverage
            )
            favoriteMovieRepository.removeFavorite(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }

    private fun checkFavorites(movieData: MutableLiveData<MovieDetail>): Disposable {

        return favoriteMovieRepository.listFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val detail = movieData.value
                    detail?.isFavorite = it.map{ d -> d.id }.contains(detail?.id)
                    movieData.value = detail
                },
                {it.message?.let { message -> Log.e("Error loading movie", message) }})
    }
}