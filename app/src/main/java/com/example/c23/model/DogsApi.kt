package com.example.c23.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DogsApi {
    @GET("random")
    suspend fun getRandomDog():Response<DogsRandomResponse>

    @GET
    suspend fun getByBreed(@Url url:String):Response<DogsRandomResponseX>
}