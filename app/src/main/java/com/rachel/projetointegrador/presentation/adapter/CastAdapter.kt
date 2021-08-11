package com.rachel.projetointegrador.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rachel.projetointegrador.R
import com.rachel.projetointegrador.data.Constants
import com.rachel.projetointegrador.data.model.Cast
import com.rachel.projetointegrador.data.model.Genre
import de.hdodenhof.circleimageview.CircleImageView

class CastAdapter (val context: Context, val dataSet: MutableList<Cast> = mutableListOf()): RecyclerView.Adapter<CastAdapter.CastViewHolder> () {

    class CastViewHolder(view: View): RecyclerView.ViewHolder(view){
        val picture = view.findViewById<CircleImageView>(R.id.imageview)
        val name = view.findViewById<TextView>(R.id.txtActorName)
        val character = view.findViewById<TextView>(R.id.txtCharacterName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val instanciaView =
            LayoutInflater.from(parent.context).inflate(R.layout.cast, parent, false)
        return CastViewHolder(instanciaView)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        Glide.with(context)
            .load("${Constants.IMAGE_BASE_URL.value}${dataSet[position].profilePath}")
            .into(holder.picture)

        holder.name.text = dataSet[position].name
        holder.character.text = dataSet[position].character
    }

    override fun getItemCount(): Int = dataSet.size
}

