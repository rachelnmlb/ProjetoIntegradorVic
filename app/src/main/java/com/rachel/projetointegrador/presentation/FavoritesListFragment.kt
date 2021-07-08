package com.rachel.projetointegrador.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rachel.projetointegrador.R
import com.rachel.projetointegrador.presentation.adapter.GenresAdapter

class FavoritesListFragment : Fragment(){

    private lateinit var rvGenresList : RecyclerView
    private lateinit var genresAdapter: GenresAdapter
    private lateinit var rvMoviesList : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvGenresList = view.findViewById(R.id.genres_list)
        val lista = mutableListOf<String>( "Ação", "Terror", "Romance", "Animação", "infantil", "Comedia")
        rvGenresList.adapter = GenresAdapter(context = view.context, dataSet = lista)
        rvGenresList.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        rvMoviesList = view.findViewById(R.id.movie_list)
        rvMoviesList.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
    }
}