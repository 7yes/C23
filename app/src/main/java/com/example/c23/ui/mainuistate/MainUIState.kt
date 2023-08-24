package com.example.c23.ui.mainuistate

sealed class MainUIState {
    object Loading : MainUIState()
    data class Error(val msg: String) : MainUIState()
    data class Sucess(val numBombitas: Int) : MainUIState()
}