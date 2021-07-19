package com.rachel.projetointegrador.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rachel.projetointegrador.databinding.ActivityDetailBinding
import com.rachel.projetointegrador.presentation.adapter.CastAdapter
import com.rachel.projetointegrador.presentation.adapter.GenresDetailAdapter
import com.rachel.projetointegrador.presentation.adapter.MovieAdapter

class DetailActivity: AppCompatActivity() {
    private lateinit var rvCastList: RecyclerView
    private lateinit var castAdapter: CastAdapter
    private lateinit var rvGenreDetail: RecyclerView
    private lateinit var genresDetailAdapter: GenresDetailAdapter
    private var movieId: Int = 0

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        movieId = intent.extras?.get(MovieAdapter.MOVIE_ID) as Int

        genresDetailAdapter = GenresDetailAdapter(this)
        rvGenreDetail = binding.genresMovieDetails
        rvGenreDetail.adapter = genresDetailAdapter
        rvGenreDetail.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        castAdapter = CastAdapter(this)
        rvCastList = binding.actorList
        rvCastList.adapter = castAdapter
        rvCastList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val detailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        val castViewModel = ViewModelProvider(this).get(CastViewModel::class.java)

        setObserverMovieDetails(detailViewModel)
        setObserverCastList(castViewModel)

        detailViewModel.loadMovieDetail(movieId)
        castViewModel.loadCastList(movieId)
    }

    private fun setObserverMovieDetails (movieDetailViewModel: MovieDetailViewModel) {
        movieDetailViewModel.movieDetail.observe(this,
            { movieDetail ->
                binding.txtMovieTitle.text = movieDetail.title
                binding.txtSinopsys.text = movieDetail.overview
                

                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500${movieDetail.backdropPath}")
                    .into(binding.imageDetailPoster)

                genresDetailAdapter.dataSet.clear()
                genresDetailAdapter.dataSet.addAll(movieDetail.genres)
                genresDetailAdapter.notifyDataSetChanged()
            }
        )
    }

    private fun setObserverCastList (castViewModel: CastViewModel){
        castViewModel.castList.observe(this,
            {castList ->
                castAdapter.dataSet.clear()
                castAdapter.dataSet.addAll(castList.cast)
                castAdapter.notifyDataSetChanged()
            })
    }

}