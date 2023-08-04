package com.example.c23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.example.c23.model.DogsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
     //   getRandomDog()
        getByBreed("akita")
    }

    private fun getByBreed(query:String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(DogsApi::class.java).getByBreed("$query/images")
            val body =call.body()
            Log.d("TAG", "getByBreed: $body")
        }
    }

    private fun getRandomDog() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(DogsApi::class.java).getRandomDog()
            val body = call.body()
            Log.d("TAG", "getRandomDog: $body")
        }
    }
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
 //           .baseUrl("https://dog.ceo/api/breeds/image/") // getRandomDog
            .baseUrl("https://dog.ceo/api/breed/") // getbyBreed
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}