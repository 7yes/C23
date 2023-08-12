package com.example.c23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.c23.core.load
import com.example.c23.databinding.ActivityMainBinding
import com.example.c23.model.CatApi
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
        getACat()
    }

    private fun getACat() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(CatApi::class.java).getACat()

            if (call.isSuccessful){
                Log.d("TAG", "getACat: ${call.body()} ")

                if(!call.body().isNullOrEmpty()){
                    val image = call.body()?.get(0)?.url
                    Log.d("TAG", "getACat: url $image ")
                    runOnUiThread{
                        binding.ivCat.load(image.toString())
                    }
                }
            }
            else {
                showError()
            }
        }
    }

    private fun showError() {
        Toast.makeText(this,"Error",Toast.LENGTH_SHORT)
    }

    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/images/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}