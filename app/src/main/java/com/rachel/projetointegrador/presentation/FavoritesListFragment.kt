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

class FavoritesListFragment : Fragment(){

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

        val genresViewModel = ViewModelProvider(this).get(GenresViewModel::class.java)
        genresAdapter = GenresAdapter(context = view.context, dataSet = mutableListOf())
        rvGenresList = view.findViewById(R.id.genres_list)
        rvGenresList.adapter = genresAdapter
        rvGenresList.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        val moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        movieAdapter = MovieAdapter(context = view.context, dataSet = mutableListOf())
        rvMoviesList = view.findViewById(R.id.movie_list)
        rvMoviesList.adapter = movieAdapter
        rvMoviesList.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        setObserverGenresList(genresViewModel)
        setObserverMovieList(moviesViewModel)
        genresViewModel.loadGenres()
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

    private fun setObserverMovieList (moviesViewModel: MoviesViewModel){
        moviesViewModel.moviesList.observe(viewLifecycleOwner,
            { movies ->
                movieAdapter.dataSet.clear()
                movieAdapter.dataSet.addAll(movies)
                movieAdapter.notifyDataSetChanged()
            }
        )
    }
}