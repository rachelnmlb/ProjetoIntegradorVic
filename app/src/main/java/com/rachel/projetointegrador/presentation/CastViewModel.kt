package com.rachel.projetointegrador.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rachel.projetointegrador.data.model.CastList
import com.rachel.projetointegrador.data.repository.CastRemoteRepository
import com.rachel.projetointegrador.data.repository.CastRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CastViewModel(private val castRepository: CastRepository) : ViewModel(){

    val castList = MutableLiveData<CastList>()

    fun loadCastList(movieId: Int): Disposable {

        return castRepository.fetchCastList(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                castList.value = it
            }, {
                it.message?.let { message -> Log.e("Error loading cast", message)}
            })
    }

}