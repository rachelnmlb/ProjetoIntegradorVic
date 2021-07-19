package com.rachel.projetointegrador.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rachel.projetointegrador.R
import com.rachel.projetointegrador.data.model.Genre

class GenresDetailAdapter (val context: Context, val dataSet: MutableList<Genre> = mutableListOf()): RecyclerView.Adapter<GenresDetailAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val genres: TextView = view.findViewById(R.id.genre_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewInstance =
            LayoutInflater.from(parent.context).inflate(R.layout.genres_details, parent, false)
        return ViewHolder(viewInstance)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.genres.text = dataSet[position].name
    }

    override fun getItemCount(): Int = dataSet.size

}