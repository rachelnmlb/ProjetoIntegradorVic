package com.rachel.projetointegrador.presentation

class FavoriteMoviesFragment : MoviesBaseFragment() {

    override fun loadMovieData() {
        moviesViewModel.loadFavoriteMovies()
    }

    override fun onGenreChange() {
        genresAdapter.onGenreCheckedChange = { genreIds ->
            if (genreIds.isEmpty())
                moviesViewModel.loadFavoriteMovies()
            else
                moviesViewModel.loadFavoritesByGenre(genreIds)
        }
    }

    override fun observeMovies() {
        moviesViewModel.favoriteMovies.observe(viewLifecycleOwner,
            { movies ->
                movieAdapter.dataSet.clear()
                movieAdapter.dataSet.addAll(movies)
                movieAdapter.notifyDataSetChanged()
            }
        )
    }
}