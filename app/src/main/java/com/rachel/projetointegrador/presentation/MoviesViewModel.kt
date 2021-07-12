package com.rachel.projetointegrador.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rachel.projetointegrador.data.Movie

class MoviesViewModel : ViewModel() {
    val moviesList : MutableLiveData<MutableList<Movie>> by lazy {
        MutableLiveData<MutableList<Movie>>(loadMovies())
    }

    private fun loadMovies() : MutableList<Movie>{
        return mutableListOf(
            Movie("Ford vs Ferrarri", "https://upload.wikimedia.org/wikipedia/pt/f/fa/Ford_v_Ferrari_poster.png"),
            Movie("Ford vs Ferrarri", "https://upload.wikimedia.org/wikipedia/pt/f/fa/Ford_v_Ferrari_poster.png"),
            Movie("Ford vs Ferrarri", "h" +
                    "ttps://upload.wikimedia.org/wikipedia/pt/f/fa/Ford_v_Ferrari_poster.png"),
            Movie("Ford vs Ferrarri", "https://upload.wikimedia.org/wikipedia/pt/f/fa/Ford_v_Ferrari_poster.png")
        )

    }
}