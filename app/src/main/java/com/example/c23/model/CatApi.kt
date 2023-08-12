package com.example.c23.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CatApi {
    @GET("search")
    suspend fun getACat():Response<CatResponse>

    @GET
    suspend fun getCaTS(@Url number: String):Response<CatResponse>
}