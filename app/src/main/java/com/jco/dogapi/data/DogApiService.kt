package com.jco.dogapi.data

import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Response<DogImageResponse>
}

data class DogImageResponse(
    val message: String,
    val status: String
)
