package com.example.c23.model.recipe

import com.example.c23.model.recipe.Location

data class RecipeResponseItem(
    val description: String,
    val ingredients: List<String>,
    val location: Location,
    val name: String,
    val photo: String
)