package com.example.c23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.c23.data.RecetasApi
import com.example.c23.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        getRecetas()
    }

    fun getRecetas(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(RecetasApi::class.java).getAllRecetas()
            if (call.isSuccessful){
                val body =call.body()
                Log.d("TAG", "getRecetas: $body ")

            }
            else{
                showError()
            }
        }
    }

    private fun showError() {
        Log.d("TAG", "showError Error")
    }

    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://demo3038897.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

