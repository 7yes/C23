package com.example.c23.data

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.c23.data.model.RecetasResponseItem
import com.example.c23.databinding.ItemRecetasBinding
import com.squareup.picasso.Picasso

class RecetasVH(view: View):RecyclerView.ViewHolder(view) {
    private val binding = ItemRecetasBinding.bind(view)
    fun bind(item: RecetasResponseItem) {
        binding.tvName.text = item.name
        Picasso.get().load(item.photo).into(binding.ivPhoto)
    }
}