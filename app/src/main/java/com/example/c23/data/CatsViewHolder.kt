package com.example.c23.data

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.c23.core.load
import com.example.c23.databinding.ItemCatBinding
import com.example.c23.model.CatResponseItem

class CatsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemCatBinding.bind(view)

    fun bind(item: CatResponseItem) {
        binding.ivCats.load(item.url)
    }
}