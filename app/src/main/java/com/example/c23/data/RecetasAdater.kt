package com.example.c23.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.c23.R
import com.example.c23.data.model.RecetasResponseItem

class RecetasAdater(val recetasList: List<RecetasResponseItem>, private val onClickLis:(RecetasResponseItem)->Unit) :
    RecyclerView.Adapter<RecetasVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetasVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecetasVH(layoutInflater.inflate(R.layout.item_recetas, parent, false))
    }

    override fun getItemCount(): Int = recetasList.size

    override fun onBindViewHolder(holder: RecetasVH, position: Int) {
        val item = recetasList[position]
        holder.bind(item, onClickLis)
    }
}