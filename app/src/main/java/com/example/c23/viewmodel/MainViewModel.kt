package com.example.c23.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.c23.data.EjemploRepo
import com.example.c23.ui.mainuistate.MainUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val repo = EjemploRepo()

    private var _uiState = MutableStateFlow<MainUIState>(MainUIState.Loading)
    val uiState: StateFlow<MainUIState> = _uiState

    fun example() { //example simple
        viewModelScope.launch(Dispatchers.IO) {
            repo.counter.collect { bombitas ->
                Log.i("TAG", "example: ${bombitas.toString()} ")
            }
        }
    }

    fun example1() { //example simple
        viewModelScope.launch(Dispatchers.IO) {
            repo.counter
                .map { it.toString() }
                .collect { bombitas ->
                    Log.i("TAG", "example1: ${bombitas} ")
                }
        }
    }

    fun example2() { //example simple
        viewModelScope.launch(Dispatchers.IO) {
            repo.counter
                .map { it.toString() }
                .onEach { save(it) }
                .catch { error ->
                    Log.d("TAG", "example2: ${error.message}")
                }
                .collect { bombitas ->
                    Log.i("TAG", "example2: ${bombitas} ")
                }
        }
    }

    private fun save(it: String) {
//
    }

    fun example3() {
        viewModelScope.launch {
            repo.counter
                .catch { _uiState.value = MainUIState.Error(it.message.orEmpty()) }
                .flowOn(Dispatchers.IO)
                .collect(){
                _uiState.value = MainUIState.Sucess(it)
            }
        }
    }
}