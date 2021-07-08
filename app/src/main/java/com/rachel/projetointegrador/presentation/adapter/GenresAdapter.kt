package com.rachel.projetointegrador.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.rachel.projetointegrador.R

class GenresAdapter(val context: Context, val dataSet: MutableList<String>) : RecyclerView.Adapter<GenresAdapter.TipoViewHolder>() {

    class TipoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genres = view.findViewById<Chip>(R.id.genres)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipoViewHolder {
        val instanciaView =
            LayoutInflater.from(parent.context).inflate(R.layout.genres, parent, false)
        return TipoViewHolder(instanciaView)
    }

    override fun onBindViewHolder(holder: TipoViewHolder, position: Int) {
        holder.genres.text = dataSet[position]
    }

    override fun getItemCount(): Int = dataSet.size

}