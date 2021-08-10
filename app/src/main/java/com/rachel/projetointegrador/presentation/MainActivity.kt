package com.rachel.projetointegrador.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.rachel.projetointegrador.R
import com.rachel.projetointegrador.presentation.adapter.FragmentAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewpager = findViewById<ViewPager>(R.id.viewpager)
        viewpager.adapter = FragmentAdapter(supportFragmentManager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewpager)

        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.updateFavorites()
    }
}