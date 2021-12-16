package com.rachel.projetointegrador.data.repository

import com.rachel.projetointegrador.data.model.GenresList
import io.reactivex.Observable

interface GenreRepository {
    fun fetchGenresList(): Observable<GenresList>
}