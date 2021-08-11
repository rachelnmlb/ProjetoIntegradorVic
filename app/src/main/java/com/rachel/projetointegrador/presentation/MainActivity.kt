package com.rachel.projetointegrador.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rachel.projetointegrador.R
import com.rachel.projetointegrador.presentation.adapter.FragmentAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewpager = findViewById<ViewPager2>(R.id.viewpager)
        viewpager.adapter = FragmentAdapter(this)
        viewpager.isUserInputEnabled = false
        viewpager.offscreenPageLimit = 2
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        TabLayoutMediator(tabLayout, viewpager, true)
        { tab, position ->
            tab.text = getPageTitle(position)
        }.attach()

        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.notifyChanges()
    }

    private fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            FragmentAdapter.TITLE_MOVIES -> getString(R.string.all_movies)
            FragmentAdapter.TITLE_FAVORITES_MOVIES -> getString(R.string.favorite_movies)
            else -> null
        }
    }
}