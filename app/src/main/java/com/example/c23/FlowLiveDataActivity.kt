package com.example.c23

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.withStarted
import com.example.c23.databinding.ActivityFlowLiveDataBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FlowLiveDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFlowLiveDataBinding
    private val viewmodel by viewModels<FlowsLivedataVM>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        suscribeObservables()

        binding.btnLivedata.setOnClickListener { viewmodel.triggerLivedata() } //observable
        binding.btnStateflow.setOnClickListener { viewmodel.triggerstateFlow() } //observable
        binding.btnFlow.setOnClickListener {
            lifecycleScope.launch {
                viewmodel.triggerFlow().collectLatest {
                    binding.tvFlow.text = it
                }
            }
        }
        binding.btnSharedflow.setOnClickListener { viewmodel.triggerSharedFlow() }
    }

    private fun suscribeObservables() {
        //Livedata
        viewmodel.liveData.observe(this) {
            binding.tvLivedata.text = it
            Snackbar.make(binding.root, "Hello from databinding ", Snackbar.LENGTH_LONG)
                .show()
        }

        //Stateflow
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.stateFlow.collect {
                    binding.tvStateflow.text = it
                    Snackbar.make(binding.root, "Hello from stateFlow ", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }

        //flow vacio xq se hace emitir desde vm o desde origen

        //SharedFlow
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.sharedFlow.collect {
                    binding.tvSharedflow.text = it
                    Snackbar.make(binding.root, "Hello from SharedFlow ", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }


    }
}