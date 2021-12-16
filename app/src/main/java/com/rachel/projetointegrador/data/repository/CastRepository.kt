package com.rachel.projetointegrador.data.repository

import com.rachel.projetointegrador.data.model.CastList
import io.reactivex.Observable

interface CastRepository {

    fun fetchCastList(movieId: Int): Observable<CastList>
}