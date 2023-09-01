package com.example.c23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.c23.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
binding.btn.setOnClickListener {  }

    }
}

val counter: Flow<Int> = flow {
    var bombitas = 1
    while (true) {
        bombitas += 1
        emit(bombitas)
        delay(1000)
    }
}