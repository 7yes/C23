package com.example.c23

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.c23.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShare.setOnClickListener { shareImage()} // llama a una de las 2 share fun,
    }

    private fun share() {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,"Whatever")
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE,"E Title")
        }
        val shareIntent = Intent.createChooser(intent,null)
        startActivity(shareIntent)
    }

    private fun shareImage() {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, Uri.EMPTY)
            type = "image/jpeg"
            putExtra(Intent.EXTRA_TITLE,"E Title")
        }
        val shareIntent = Intent.createChooser(intent,null)
        startActivity(shareIntent)
    }
}