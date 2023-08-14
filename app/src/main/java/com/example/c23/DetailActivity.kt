package com.example.c23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.c23.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("NAME")
        val photo = intent.getStringExtra("PHOTO")
        val desc = intent.getStringExtra("DESCR")

        binding.tvName.text = name
        Picasso.get().load(photo).into(binding.ivPhoto)
        binding.tvDescription.text = desc
    }
}