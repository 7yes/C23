package com.example.c23.model

import retrofit2.Response
import retrofit2.http.GET

interface CatApi {
    @GET("search")
    suspend fun getACat():Response<CatResponse>
}