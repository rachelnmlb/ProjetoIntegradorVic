package com.rachel.projetointegrador.presentation

import android.view.View

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
                if (movies.isNotEmpty()) {
                    movieAdapter.dataSet.clear()
                    movieAdapter.dataSet.addAll(movies)
                    movieAdapter.notifyDataSetChanged()
                    showResults()
                } else {
                    showNotFound()
                }
            }
        )
    }

    private fun showNotFound() {
        binding.movieList.visibility = View.GONE
        binding.notFoundNotification.visibility = View.VISIBLE
    }

    private fun showResults() {
        binding.movieList.visibility = View.VISIBLE
        binding.notFoundNotification.visibility = View.GONE
    }
}