package com.rachel.projetointegrador.data.repository

import com.rachel.projetointegrador.data.model.MoviesList
import io.reactivex.Observable

class MovieRepository {
    fun fetchMoviesList(): Observable<MoviesList>{
        return Network.getService().getPopularMoviesList()
    }

    fun fetchMovieByGenre(genreIds: String): Observable<MoviesList>{
        return Network.getService().getMovieByGenre(genreIds)
    }
}