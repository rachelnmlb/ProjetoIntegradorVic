package com.rachel.projetointegrador.data.repository

import com.rachel.projetointegrador.data.model.GenresList
import com.rachel.projetointegrador.data.model.MoviesList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {

    @GET("genre/movie/list")
    fun getGenreList(): Observable<GenresList>

    @GET("movie/popular")
    fun getPopularMoviesList(): Observable<MoviesList>

    @GET("discover/movie")
    fun getMovieByGenre(@Query(value = "with_genres", encoded = true) genreIds: String): Observable<MoviesList>

}