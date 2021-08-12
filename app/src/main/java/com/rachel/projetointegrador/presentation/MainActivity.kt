package com.rachel.projetointegrador.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.rachel.projetointegrador.R
import com.rachel.projetointegrador.databinding.ActivityHomeBinding
import com.rachel.projetointegrador.presentation.adapter.FragmentAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var moviesViewModel: MoviesViewModel

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val searchFragment by lazy {
        SearchMoviesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        configViewPager()
        addSearchFragment()
        bindEvents()
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.notifyChanges()
    }

    private fun addSearchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.searchContainer.id, searchFragment)
            .commit()
    }

    private fun configViewPager() {

        binding.viewpager.apply {
            adapter = FragmentAdapter(this@MainActivity)
            isUserInputEnabled = false
            offscreenPageLimit = 2
        }

        TabLayoutMediator(binding.tabLayout, binding.viewpager)
        { tab, position ->
            tab.text = getPageTitle(position)
        }.attach()
    }

    private fun bindEvents() {
        binding.searchButton.setOnClickListener {
            val query = binding.search.text.toString()

            if (!query.isNullOrEmpty()) {
                enableSearchMode()
                moviesViewModel.searchMovies(query)
            }
        }

        binding.back.setOnClickListener {
            binding.search.text.clear()
            disableSearchMode()
        }
    }

    private fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            FragmentAdapter.TITLE_MOVIES -> getString(R.string.all_movies)
            FragmentAdapter.TITLE_FAVORITES_MOVIES -> getString(R.string.favorite_movies)
            else -> null
        }
    }

    private fun enableSearchMode() {
        binding.viewpager.visibility = View.GONE
        binding.tabLayout.visibility = View.GONE

        binding.searchContainer.visibility = View.VISIBLE
        binding.imageRetangle.visibility = View.VISIBLE
        binding.searchMode.visibility = View.VISIBLE
        binding.back.visibility = View.VISIBLE
    }

    private fun disableSearchMode() {
        binding.viewpager.visibility = View.VISIBLE
        binding.tabLayout.visibility = View.VISIBLE

        binding.searchContainer.visibility = View.GONE
        binding.imageRetangle.visibility = View.GONE
        binding.searchMode.visibility = View.GONE
        binding.back.visibility = View.GONE
    }
}