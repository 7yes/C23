package com.example.c23.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.c23.R
import com.example.c23.model.CatResponseItem

class CatsAdapter(val images: List<CatResponseItem>):RecyclerView.Adapter<CatsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        return CatsViewHolder(layoutInflater.inflate(R.layout.item_cat,parent,false))
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        val item =images[position]
        holder.bind(item)
    }
}