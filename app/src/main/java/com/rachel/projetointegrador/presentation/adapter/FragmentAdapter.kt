package com.rachel.projetointegrador.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rachel.projetointegrador.presentation.FavoritesListFragment
import com.rachel.projetointegrador.presentation.MoviesListFragment

class FragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> MoviesListFragment()
            1 -> FavoritesListFragment()
            else -> MoviesListFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Todos os Filmes"
            1 -> "Favoritos"
            else -> null
        }
    }

}