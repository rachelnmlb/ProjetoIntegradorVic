package com.rachel.projetointegrador.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rachel.projetointegrador.data.Genre

class GenresViewModel : ViewModel() {

    val genresList: MutableLiveData<MutableList<Genre>> by lazy {
        MutableLiveData<MutableList<Genre>>(loadGenres())
    }

    private fun loadGenres(): MutableList<Genre> {
        return mutableListOf(
            Genre(1, "Ação"),
            Genre(2, "Terror"),
            Genre(3, "Romance"),
            Genre(4, "Animação"),
            Genre(5, "Comédia"),
            Genre(6, "Infantil")
        )
    }
}