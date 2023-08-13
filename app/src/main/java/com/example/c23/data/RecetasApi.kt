package com.example.c23.data

import com.example.c23.data.model.RecetasResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface RecetasApi {
    @GET("recetas")
    suspend fun getAllRecetas():Response<List<RecetasResponseItem>>
}