package com.rachel.projetointegrador.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rachel.projetointegrador.R
import com.rachel.projetointegrador.presentation.adapter.FragmentAdapter
import com.rachel.projetointegrador.presentation.adapter.GenresAdapter
import com.rachel.projetointegrador.presentation.adapter.MovieAdapter

class MoviesListFragment : Fragment() {

    private lateinit var rvGenresList : RecyclerView
    private lateinit var rvMoviesList : RecyclerView
    private lateinit var genresAdapter : GenresAdapter
    private lateinit var movieAdapter : MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val movieListType = arguments?.get(FragmentAdapter.MOVIE_LIST_TYPE)
        val moviesViewModel: MoviesViewModel = getMoviesViewModel(movieListType)
        val genresViewModel = ViewModelProvider(this).get(GenresViewModel::class.java)

        movieAdapter = MovieAdapter(context = view.context, dataSet = mutableListOf())
        rvMoviesList = view.findViewById(R.id.movie_list)
        rvMoviesList.adapter = movieAdapter
        rvMoviesList.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        genresAdapter = GenresAdapter(context = view.context, dataSet = mutableListOf())
        rvGenresList = view.findViewById(R.id.genres_list)
        rvGenresList.adapter = genresAdapter
        rvGenresList.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        genresAdapter.onCheckedListener = { genreIds ->
            if (genreIds.isEmpty())
                moviesViewModel.loadMovies()
            else
                moviesViewModel.loadMoviesByGenre(genreIds)
        }

        setObserverGenresList(genresViewModel)
        setObserverMovieList(moviesViewModel)
        genresViewModel.loadGenres()
        moviesViewModel.loadMovies()
    }

    private fun setObserverGenresList(genresViewModel: GenresViewModel) {
        genresViewModel.genresList.observe(viewLifecycleOwner,
            { genres ->
                genresAdapter.dataSet.clear()
                genresAdapter.dataSet.addAll(genres)
                genresAdapter.notifyDataSetChanged()
            }
        )
    }

    private fun setObserverMovieList (moviesViewModel: MoviesViewModel) {
        moviesViewModel.moviesList.observe(viewLifecycleOwner,
            { movies ->
                movieAdapter.dataSet.clear()
                movieAdapter.dataSet.addAll(movies)
                movieAdapter.notifyDataSetChanged()
            }
        )
    }

    private fun getMoviesViewModel(movieListType: Any?): MoviesViewModel {
        return when (movieListType) {
            FragmentAdapter.POPULAR_MOVIES_LIST ->
                ViewModelProvider(this).get(PopularMoviesViewModel::class.java)
            FragmentAdapter.FAVORITES_MOVIES_LIST ->
                ViewModelProvider(this).get(FavoriteMoviesViewModel::class.java)
            else ->
                ViewModelProvider(this).get(PopularMoviesViewModel::class.java)
        }
    }
}