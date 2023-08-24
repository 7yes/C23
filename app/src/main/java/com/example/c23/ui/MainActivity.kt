package com.example.c23.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.c23.databinding.ActivityMainBinding
import com.example.c23.ui.mainuistate.MainUIState
import com.example.c23.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.example()
        viewModel.example1()
        viewModel.example2()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is MainUIState.Error -> {
                            binding.progress.isVisible = false
                            Toast.makeText(this@MainActivity,"Hubo un error: ${it.msg}",Toast.LENGTH_SHORT).show()
                        }

                        MainUIState.Loading -> {
                            binding.progress.isVisible =true
                        }
                        is MainUIState.Sucess -> {
                            binding.progress.isVisible = false
                            Toast.makeText(this@MainActivity,"result: ${it.numBombitas}",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        viewModel.example3()
    }
}