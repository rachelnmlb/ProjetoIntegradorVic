package com.rachel.projetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rachel.projetointegrador.R
import com.rachel.projetointegrador.data.Constants
import com.rachel.projetointegrador.data.RequestStatus
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

    private lateinit var detailViewModel: MovieDetailViewModel
    private lateinit var castViewModel: CastViewModel

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        movieId = intent.extras?.get(MovieAdapter.MOVIE_ID) as Int

        genresDetailAdapter = GenresDetailAdapter(this)
        binding.genresMovieDetails.adapter = genresDetailAdapter

        castAdapter = CastAdapter(this)
        binding.actorList.adapter = castAdapter

        detailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        castViewModel = ViewModelProvider(this).get(CastViewModel::class.java)

        observeMovieDetails()
        observeCastList()
        observeRequestStatus()

        detailViewModel.loadMovieDetail(movieId)
        castViewModel.loadCastList(movieId)

        binding.btnReturn.setOnClickListener {
            onBackPressed()
        }

        binding.favoriteDetail.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                detailViewModel.addFavorite()
            else
                detailViewModel.removeFavorite()
        }
    }

    private fun observeMovieDetails () {
        detailViewModel.movieDetail.observe(this,
            { movieDetail ->
                binding.txtMovieTitle.text = movieDetail.title
                binding.txtSinopsys.text = movieDetail.overview
                binding.favoriteDetail.isChecked = movieDetail.isFavorite
                binding.txtMovieYear.text = movieDetail.getReleaseYear()

                val rating = movieDetail.rating * 10.0
                binding.txtNumberPercent.text = "${"%.0f".format(rating)}%"

                Glide.with(this)
                    .load("${Constants.IMAGE_BASE_URL.value}${movieDetail.backdropPath}")
                    .placeholder(R.drawable.poster_fallback)
                    .fallback(R.drawable.poster_fallback)
                    .into(binding.imageDetailPoster)

                genresDetailAdapter.dataSet.clear()
                genresDetailAdapter.dataSet.addAll(movieDetail.genres)
                genresDetailAdapter.notifyDataSetChanged()
            }
        )
    }

    private fun observeCastList (){
        castViewModel.castList.observe(this,
            {castList ->
                castAdapter.dataSet.clear()
                castAdapter.dataSet.addAll(castList.cast)
                castAdapter.notifyDataSetChanged()
            })
    }

    private fun observeRequestStatus() {
        detailViewModel.requestStatus.observe(this,
            { requestStatus ->
                if (requestStatus == RequestStatus.ERROR) {
                    val intent = Intent(this, SystemFailedActivity::class.java)
                    startActivity(intent)
                }
            }
        )
    }

}