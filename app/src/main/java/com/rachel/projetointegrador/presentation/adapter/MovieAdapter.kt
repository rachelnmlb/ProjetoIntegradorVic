package com.rachel.projetointegrador.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rachel.projetointegrador.R
import com.rachel.projetointegrador.data.model.Movie

class MovieAdapter(val context: Context, val dataSet: MutableList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder> () {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val poster = view.findViewById<ImageView>(R.id.btn_img_movie)
        val title = view.findViewById<TextView>(R.id.title_movie)
        val voteAverage = view.findViewById<TextView>(R.id.rate_movie)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val instanciaView =
            LayoutInflater.from(parent.context).inflate(R.layout.movie, parent, false)
        return MovieViewHolder(instanciaView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val rating = (dataSet[position].voteAverage * 10.0)

        holder.voteAverage.text = "${"%.0f".format(rating)}%"
        holder.title.text = dataSet[position].title

        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500${dataSet[position].posterPath}")
            .into(holder.poster)
    }

    override fun getItemCount(): Int = dataSet.size
}