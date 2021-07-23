package com.rachel.projetointegrador.presentation

import com.rachel.projetointegrador.data.repository.FavoriteMovieRepository

class FavoriteMoviesViewModel : MoviesViewModel() {


    override fun loadMovies() {
        val favorites = FavoriteMovieRepository.listFavorites()
        updateValue(favorites)
    }

    override fun loadMoviesByGenre(genreIds: List<Int>) {

    }
}