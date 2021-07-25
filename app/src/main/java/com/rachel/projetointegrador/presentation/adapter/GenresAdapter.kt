package com.rachel.projetointegrador.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.google.android.material.chip.Chip
import com.rachel.projetointegrador.R
import com.rachel.projetointegrador.data.model.Genre

class GenresAdapter(val context: Context, val dataSet: MutableList<Genre>) : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {
    var onGenreCheckedChange : (genreIds: List<Int>) -> Unit = {}
    private val idGenresList: MutableList<Int> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genres: Chip = view.findViewById(R.id.genres)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewInstance =
            LayoutInflater.from(parent.context).inflate(R.layout.genres, parent, false)
        return ViewHolder(viewInstance)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = dataSet[position]
        holder.genres.text = genre.name

        holder.genres.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                idGenresList.add(genre.id)
            } else {
                idGenresList.remove(genre.id)
            }
            onGenreCheckedChange(idGenresList)
        }
    }

    override fun getItemCount(): Int = dataSet.size
}