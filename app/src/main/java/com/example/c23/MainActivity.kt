package com.example.c23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.c23.core.load
import com.example.c23.data.CatsAdapter
import com.example.c23.databinding.ActivityMainBinding
import com.example.c23.model.CatApi
import com.example.c23.model.CatResponseItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: CatsAdapter
    private val catImages = mutableListOf<CatResponseItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
    }

    private fun initRV() {
        adapter = CatsAdapter(catImages)
        binding.rvCats.layoutManager = LinearLayoutManager(this)
        binding.rvCats.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        getACat()
        getCats("10")
    }

    private fun getCats(number: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(CatApi::class.java).getCaTS("search?limit=$number")
            Log.d("TAG", "getCats: ${call.body()} ")
            val kitties = call.body()
            Log.d("TAG", "getCats: kitties $kitties ")
            if (call.isSuccessful) {

                runOnUiThread {

                    catImages.clear()
                    if (kitties != null) {
                        catImages.addAll(kitties)
                    }
                    adapter.notifyDataSetChanged()
                }

            } else {
                showError()
            }
        }
    }

    private fun getACat() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(CatApi::class.java).getACat()

            if (call.isSuccessful) {
                Log.d("TAG", "getACat: ${call.body()} ")

                if (!call.body().isNullOrEmpty()) {
                    val image = call.body()?.get(0)?.url
                    Log.d("TAG", "getACat: url $image ")
                    runOnUiThread {
                        binding.ivCat.load(image.toString())
                    }
                }
            } else {
                showError()
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT)
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/images/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
