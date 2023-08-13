package com.example.c23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.c23.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreate.setOnClickListener {
            //
        }
        binding.btnUpdate.setOnClickListener {
            //
        }
        binding.btnCancel.setOnClickListener {

        }

    }
}