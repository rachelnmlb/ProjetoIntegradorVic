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
import com.rachel.projetointegrador.presentation.adapter.GenresAdapter
import com.rachel.projetointegrador.presentation.adapter.MovieAdapter

class PopularMoviesFragment : Fragment() {

    private lateinit var rvGenresList : RecyclerView
    private lateinit var rvMoviesList : RecyclerView
    private lateinit var genresAdapter : GenresAdapter
    private lateinit var movieAdapter : MovieAdapter
    private lateinit var moviesViewModel : MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        moviesViewModel = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)

        movieAdapter = MovieAdapter(view.context, mutableListOf())
        rvMoviesList = view.findViewById(R.id.movie_list)
        rvMoviesList.adapter = movieAdapter
        rvMoviesList.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        genresAdapter = GenresAdapter(view.context, mutableListOf())
        rvGenresList = view.findViewById(R.id.genres_list)
        rvGenresList.adapter = genresAdapter
        rvGenresList.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        genresAdapter.onGenreCheckedChange = { genreIds ->
            if (genreIds.isEmpty())
                moviesViewModel.loadPopularMovies()
            else
                moviesViewModel.loadMoviesByGenre(genreIds)
        }

        movieAdapter.onFavoriteCheckedChange = { movie, isChecked ->
            if (isChecked)
                moviesViewModel.addFavorite(movie)
            else
                moviesViewModel.removeFavorite(movie.id)
        }

        setObservers()
        moviesViewModel.loadPopularMovies()
        moviesViewModel.loadGenres()
    }

    private fun setObservers () {

        moviesViewModel.popularMovies.observe(viewLifecycleOwner,
            { movies ->
                movieAdapter.dataSet.clear()
                movieAdapter.dataSet.addAll(movies)
                movieAdapter.notifyDataSetChanged()
            }
        )

        moviesViewModel.genresList.observe(viewLifecycleOwner,
            { genres ->
                genresAdapter.dataSet.clear()
                genresAdapter.dataSet.addAll(genres)
                genresAdapter.notifyDataSetChanged()
            }
        )
    }
}