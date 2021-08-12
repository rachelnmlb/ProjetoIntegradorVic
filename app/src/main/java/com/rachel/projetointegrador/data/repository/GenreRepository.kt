package com.rachel.projetointegrador.data.repository

import com.rachel.projetointegrador.data.model.GenresList
import io.reactivex.Observable

object GenreRepository {

    fun fetchGenresList(): Observable<GenresList> {
        return Network.getService().getGenreList()
    }
}