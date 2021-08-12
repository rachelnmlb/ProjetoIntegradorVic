package com.rachel.projetointegrador.data.repository

import com.rachel.projetointegrador.data.model.CastList
import io.reactivex.Observable

object CastRepository {
    fun fetchCastList(movieId: Int): Observable<CastList>{
        return Network.getService().getMovieCast(movieId)
    }
}