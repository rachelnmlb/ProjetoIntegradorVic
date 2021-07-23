package com.rachel.projetointegrador.presentation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rachel.projetointegrador.data.Constants
import com.rachel.projetointegrador.presentation.MoviesListFragment

class FragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object{
        const val POPULAR_MOVIES_LIST = 0
        const val FAVORITES_MOVIES_LIST = 1
        const val TITLE_MOVIES = 0
        const val TITLE_FAVORITES_MOVIES = 1
        const val MOVIE_LIST_TYPE = "MOVIE_LIST_TYPE"
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        val fragment = MoviesListFragment()

        var bundle = Bundle()
        bundle.putInt(MOVIE_LIST_TYPE, position)

        fragment.arguments = bundle

        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            TITLE_MOVIES -> "Todos os Filmes"
            TITLE_FAVORITES_MOVIES -> "Favoritos"
            else -> null
        }
    }

}