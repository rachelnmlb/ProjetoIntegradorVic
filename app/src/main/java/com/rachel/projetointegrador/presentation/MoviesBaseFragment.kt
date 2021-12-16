package com.rachel.projetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rachel.projetointegrador.data.RequestStatus
import com.rachel.projetointegrador.databinding.FragmentMoviesBinding
import com.rachel.projetointegrador.presentation.adapter.GenresAdapter
import com.rachel.projetointegrador.presentation.adapter.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class MoviesBaseFragment : Fragment() {

    protected lateinit var genresAdapter : GenresAdapter
    protected lateinit var movieAdapter : MovieAdapter
    protected val moviesViewModel : MoviesViewModel by viewModel()

    private var _binding: FragmentMoviesBinding? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        movieAdapter = MovieAdapter(view.context, mutableListOf())
        binding.movieList.adapter = movieAdapter

        genresAdapter = GenresAdapter(view.context, mutableListOf())
        binding.genresList.adapter = genresAdapter

        bindEvents()
        setObservers()
        loadMovieData()
        loadGenreData()
    }

    fun scrollToTop() {
        binding.movieList.smoothScrollToPosition(0)
    }

    protected abstract fun observeMovies()
    protected abstract fun loadMovieData()
    protected abstract fun onGenreChange()

    private fun bindEvents() {

        onGenreChange()
        onFavoriteChange()

        moviesViewModel.requestStatus.observe(viewLifecycleOwner,
            { requestStatus ->
                if (requestStatus == RequestStatus.ERROR) {
                    val intent = Intent(requireContext(), SystemFailedActivity::class.java)
                    startActivity(intent)
                }
            }
        )
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