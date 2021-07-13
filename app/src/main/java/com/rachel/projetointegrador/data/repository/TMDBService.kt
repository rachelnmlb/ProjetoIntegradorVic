package com.rachel.projetointegrador.data.repository

import com.rachel.projetointegrador.data.model.GenresList
import io.reactivex.Observable
import retrofit2.http.GET

interface TMDBService {

    @GET("genre/movie/list")
    fun getGenreList(): Observable<GenresList>
}