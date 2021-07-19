package com.rachel.projetointegrador.data.repository

import com.rachel.projetointegrador.data.model.CastList
import io.reactivex.Observable

class CastRepository {
    fun fetchCastList(movieId: Int): Observable<CastList>{
        return Network.getService().getMovieCast(movieId)
    }
}