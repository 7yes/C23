package com.example.c23.model.recipe

import com.example.c23.model.recipe.RecipeResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface RecipesApi {
    @GET("recetas")
    suspend fun getAllRecipes(): Response<List<RecipeResponseItem>>
}