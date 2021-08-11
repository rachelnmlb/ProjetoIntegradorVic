package com.rachel.projetointegrador.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rachel.projetointegrador.data.model.Movie
import com.rachel.projetointegrador.data.model.MovieDetail
import com.rachel.projetointegrador.data.repository.FavoriteMovieRepository
import com.rachel.projetointegrador.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailViewModel: ViewModel() {
    private val detailRepository = MovieRepository()
    private val favoriteMoviesRepository = FavoriteMovieRepository()

    private val _movieDetail = MutableLiveData<MovieDetail>()

    val movieDetail: LiveData<MovieDetail> = _movieDetail


    fun loadMovieDetail(movieId: Int): Disposable{
        return detailRepository.fetchMovieDetail(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                it.message?.let { message -> Log.e("Error loading movies", message) }
            }
            .subscribe {
                it.isFavorite = favoriteMoviesRepository.isFavorite(it.id)
                _movieDetail.value = it
            }
    }

    fun addFavorite() {
        _movieDetail.value?.let {
            val movie = Movie(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                genreIds = it.genres.map { genre -> genre.id },
                voteAverage = it.rating
            )
            favoriteMoviesRepository.addFavorite(movie)
        }
    }

    fun removeFavorite() {
        _movieDetail.value?.let {
            favoriteMoviesRepository.removeFavorite(it.id)
        }
    }
}