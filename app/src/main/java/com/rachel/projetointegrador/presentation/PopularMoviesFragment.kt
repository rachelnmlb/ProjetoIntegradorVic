package com.rachel.projetointegrador.presentation

class PopularMoviesFragment : MoviesBaseFragment() {

    override fun loadMovieData() {
        moviesViewModel.loadPopularMovies()
    }

    override fun onGenreChange() {
        genresAdapter.onGenreCheckedChange = { genreIds ->
            if (genreIds.isEmpty())
                moviesViewModel.loadPopularMovies()
            else
                moviesViewModel.loadMoviesByGenre(genreIds)
        }
    }

    override fun observeMovies() {
        moviesViewModel.popularMovies.observe(viewLifecycleOwner,
            { movies ->
                movieAdapter.dataSet.clear()
                movieAdapter.dataSet.addAll(movies)
                movieAdapter.notifyDataSetChanged()
            }
        )
    }
}