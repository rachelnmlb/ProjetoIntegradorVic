package com.rachel.projetointegrador.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rachel.projetointegrador.presentation.FavoriteMoviesFragment
import com.rachel.projetointegrador.presentation.PopularMoviesFragment

class FragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object{
        const val POPULAR_MOVIES_LIST = 0
        const val FAVORITES_MOVIES_LIST = 1
        const val TITLE_MOVIES = 0
        const val TITLE_FAVORITES_MOVIES = 1
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            POPULAR_MOVIES_LIST -> PopularMoviesFragment()
            FAVORITES_MOVIES_LIST -> FavoriteMoviesFragment()
            else -> PopularMoviesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            TITLE_MOVIES -> "Todos os Filmes"
            TITLE_FAVORITES_MOVIES -> "Favoritos"
            else -> null
        }
    }
}