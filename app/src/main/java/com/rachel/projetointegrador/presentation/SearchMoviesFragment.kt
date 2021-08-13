package com.rachel.projetointegrador.presentation

class SearchMoviesFragment : MoviesBaseFragment() {

    override fun loadMovieData() {
    }

    override fun onGenreChange() {
        genresAdapter.onGenreCheckedChange = { genreIds ->
            if (genreIds.isEmpty())
                moviesViewModel.repeatLastSearch()
            else
                moviesViewModel.filterSearchResultsByGenre(genreIds)
        }
    }

    override fun observeMovies() {
        moviesViewModel.searchResults.observe(viewLifecycleOwner,
            { movies ->
                movieAdapter.dataSet.clear()
                movieAdapter.dataSet.addAll(movies)
                movieAdapter.notifyDataSetChanged()
            }
        )
    }
}