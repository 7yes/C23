package com.example.c23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.c23.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "onStart: ")

        //llamada secuencial
        runBlocking { val result1 = call1()
            Log.d("TAG", "onStart: $result1")
            val result2 = call2()
            Log.d("TAG", "onStart: $result2")
        }

       CoroutineScope(Dispatchers.IO).launch {
           Log.d("TAG", "onStartA Coroutina")
           val result1= call1()
           Log.d("TAG", "onStartA: $result1")
           val result2= call2()
           Log.d("TAG", "onStartA: $result2")
       }

        CoroutineScope(Dispatchers.IO).launch {
            Log.d("TAG", "onStartB Coroutina2")
            val result1= call1()
            Log.d("TAG", "onStartB: $result1")
        }
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("TAG", "onStartC Coroutina3")
            val result2= call2()
            Log.d("TAG", "onStartC: $result2")
        }

        CoroutineScope(Dispatchers.IO).async {
            Log.d("TAG", "onStart Coroutina async")
            val result1= call1()
            Log.d("TAG", "onStart async: $result1")
            val result2= call2()
            Log.d("TAG", "onStart async: $result2")
        }



    }

    suspend fun call1(): Int {
        delay(2000)
        return 1
    }

   suspend fun call2(): Int {
        delay(1000)
        return 2
    }
}