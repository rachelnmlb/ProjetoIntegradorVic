package com.rachel.projetointegrador.presentation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rachel.projetointegrador.R
import com.rachel.projetointegrador.data.Constants
import com.rachel.projetointegrador.data.model.Movie
import com.rachel.projetointegrador.presentation.DetailActivity

class MovieAdapter(val context: Context, val dataSet: MutableList<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder> () {

    var onFavoriteCheckedChange : (movie: Movie, isChecked: Boolean) -> Unit = { _, _ ->}

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val poster: ImageView = view.findViewById(R.id.btn_img_movie)
        val title: TextView = view.findViewById(R.id.title_movie)
        val voteAverage: TextView = view.findViewById(R.id.rate_movie)
        val favorite: ToggleButton = view.findViewById(R.id.favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewInstance =
            LayoutInflater.from(parent.context).inflate(R.layout.movie, parent, false)
        return ViewHolder(viewInstance)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = dataSet[position]

        holder.voteAverage.text = movie.voteAveragePercent()
        holder.title.text = movie.title

        Glide.with(context)
            .load("${Constants.IMAGE_BASE_URL.value}${movie.posterPath}")
            .placeholder(R.drawable.poster_fallback)
            .fallback(R.drawable.poster_fallback)
            .into(holder.poster)

        holder.poster.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_ID, movie.id)
            context.startActivity(intent)
        }

        holder.favorite.setOnCheckedChangeListener(null)

        holder.favorite.isChecked = movie.isFavorite

        holder.favorite.setOnCheckedChangeListener { _, isChecked ->
            onFavoriteCheckedChange(movie, isChecked)
        }
    }

    override fun getItemCount(): Int = dataSet.size

    companion object {
        const val MOVIE_ID = "movieId"
    }
}