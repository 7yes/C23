package com.example.c23

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.c23.databinding.ActivityMainBinding
import com.example.ui.components.QuantityDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnButton.setOnClickListener {

        }

        binding.btnButton.setOnClickListener {
            val dialog = QuantityDialog(
                onSubmitClickLis = {
                    Toast.makeText(this, "typed q: $it ", Toast.LENGTH_SHORT).show()
                }
            ).show(supportFragmentManager, "dialog") //parentFragmentmanager en Frag
        }
    }
}
