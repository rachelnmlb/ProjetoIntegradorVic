package com.rachel.projetointegrador.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rachel.projetointegrador.presentation.FavoriteMoviesFragment
import com.rachel.projetointegrador.presentation.PopularMoviesFragment

class FragmentAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    companion object{
        const val POPULAR_MOVIES_LIST = 0
        const val FAVORITES_MOVIES_LIST = 1
        const val TITLE_MOVIES = 0
        const val TITLE_FAVORITES_MOVIES = 1
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            POPULAR_MOVIES_LIST -> PopularMoviesFragment()
            FAVORITES_MOVIES_LIST -> FavoriteMoviesFragment()
            else -> PopularMoviesFragment()
        }
    }
}