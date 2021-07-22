package com.rachel.projetointegrador.presentation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rachel.projetointegrador.presentation.FavoritesListFragment
import com.rachel.projetointegrador.presentation.MoviesListFragment
import com.rachel.projetointegrador.presentation.MoviesViewModel

class FragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object{
        const val MOVIES_LIST = 0
        const val FAVORITES_MOVIES_LIST = 1
        const val TITLE_MOVIES = 0
        const val TITLE_FAVORITES_MOVIES=1
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            MOVIES_LIST -> MoviesListFragment()
            FAVORITES_MOVIES_LIST-> FavoritesListFragment()
            else -> MoviesListFragment()
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