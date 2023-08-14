package com.example.c23

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.c23.data.RecetasAdater
import com.example.c23.data.RecetasApi
import com.example.c23.data.model.RecetasResponseItem
import com.example.c23.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adater: RecetasAdater
    private var recetasList = mutableListOf<RecetasResponseItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
    }

    private fun initRV() {
        adater = RecetasAdater(recetasList){onItemSelected(it)}
        binding.rvRecetas.layoutManager = LinearLayoutManager(this)
        binding.rvRecetas.adapter = adater
    }

    private fun onItemSelected(it: RecetasResponseItem) {
Toast.makeText(this,it.name,Toast.LENGTH_SHORT).show()
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("NAME",it.name)
        intent.putExtra("PHOTO",it.photo)
        intent.putExtra("DESCR",it.description)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        getRecetas()
    }

    fun getRecetas() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(RecetasApi::class.java).getAllRecetas()
            if (call.isSuccessful) {
                val body = call.body()
                Log.d("TAG", "getRecetas: $body ")

                body?.let {
                    recetasList.clear()
                    recetasList.addAll(it)
                    runOnUiThread { adater.notifyDataSetChanged() }
                }

            } else {
                showError()
            }
        }
    }

    private fun showError() {
        Log.d("TAG", "showError Error")
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://demo3038897.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

