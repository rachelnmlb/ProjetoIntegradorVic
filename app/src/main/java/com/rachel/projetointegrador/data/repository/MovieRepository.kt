package com.rachel.projetointegrador.data.repository

import com.rachel.projetointegrador.data.model.MovieDetail
import com.rachel.projetointegrador.data.model.MoviesList
import io.reactivex.Observable

object MovieRepository {
    fun fetchMoviesList(): Observable<MoviesList>{
        return Network.getService().getPopularMoviesList()
    }

    fun fetchMovieByGenre(genreIds: List<Int>): Observable<MoviesList>{
        return Network.getService().getMovieByGenre(genreIds.joinToString(","))
    }

    fun fetchMovieDetail( movieId: Int): Observable<MovieDetail> {
        return Network.getService().getMovieDetail(movieId)
    }

    fun searchMovies(query: String): Observable<MoviesList> {
        return Network.getService().searchMovies(query)
    }
}