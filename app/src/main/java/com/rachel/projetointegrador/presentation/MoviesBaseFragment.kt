package com.rachel.projetointegrador.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rachel.projetointegrador.databinding.FragmentMoviesBinding
import com.rachel.projetointegrador.presentation.adapter.GenresAdapter
import com.rachel.projetointegrador.presentation.adapter.MovieAdapter

abstract class MoviesBaseFragment : Fragment() {

    protected lateinit var genresAdapter : GenresAdapter
    protected lateinit var movieAdapter : MovieAdapter
    protected lateinit var moviesViewModel : MoviesViewModel

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        moviesViewModel = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)

        movieAdapter = MovieAdapter(view.context, mutableListOf())
        binding.movieList.adapter = movieAdapter
        binding.movieList.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        genresAdapter = GenresAdapter(view.context, mutableListOf())
        binding.genresList.adapter = genresAdapter
        binding.genresList.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        bindEvents()
        setObservers()
        loadMovieData()
        loadGenreData()
    }

    protected abstract fun observeMovies()
    protected abstract fun loadMovieData()
    protected abstract fun onGenreChange()

    private fun bindEvents() {

        onGenreChange()
        onFavoriteChange()
    }

    private fun onFavoriteChange() {
        movieAdapter.onFavoriteCheckedChange = { movie, isChecked ->
            if (isChecked)
                moviesViewModel.addFavorite(movie)
            else
                moviesViewModel.removeFavorite(movie)
        }
    }

    private fun setObservers() {
        observeMovies()
        observeGenres()
    }

    private fun loadGenreData() {
        moviesViewModel.loadGenres()
    }

    private fun observeGenres() {
        moviesViewModel.genresList.observe(viewLifecycleOwner,
            { genres ->
                genresAdapter.dataSet.clear()
                genresAdapter.dataSet.addAll(genres)
                genresAdapter.notifyDataSetChanged()
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}