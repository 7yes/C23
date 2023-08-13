package com.example.c23.data.model

data class RecetasResponseItem(
    val description: String,
    val ingredients: List<String>,
    val location: Location,
    val name: String,
    val photo: String
)