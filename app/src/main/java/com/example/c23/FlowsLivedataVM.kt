package com.example.c23

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FlowsLivedataVM:ViewModel() {
    private val _liveData  = MutableLiveData<String>("Hello")
    val liveData: LiveData<String> = _liveData

    private val _stateFlow  = MutableStateFlow("Hello")
    val stateFlow: StateFlow<String> = _stateFlow

    private val _sharedFlow  = MutableSharedFlow<String>()
    val sharedFlow: SharedFlow<String> = _sharedFlow

    fun triggerLivedata() {
        _liveData.value ="LiveData"
    }

    fun triggerstateFlow() {
        _stateFlow.value = "StateFlow"
    }

    fun triggerFlow():Flow<String>{
        return flow{
            repeat(5){
                emit(" valor $it")
                delay(1000)
            }
        }
    }

    fun triggerSharedFlow() {
       viewModelScope.launch {
           _sharedFlow.emit("SharedFlow")
       }
    }

}