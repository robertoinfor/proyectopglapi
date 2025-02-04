package com.jco.dogapi.repository

import com.jco.dogapi.room.DogDao
import com.jco.dogapi.data.DogApiService
import com.jco.dogapi.data.DogImageResponse
import com.jco.dogapi.room.DogCacheEntity
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val dogApiService: DogApiService,
    private val dogDao: DogDao
) {

    suspend fun getRandomDogImage(): DogImageResponse {
        val response = dogApiService.getRandomDogImage()
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("No data found")
        } else {
            throw Exception("Failed to fetch data")
        }
    }

    suspend fun insertDog(dog: DogCacheEntity) {
        dogDao.insertDog(dog)
    }

    suspend fun deleteDog(url: String) {
        dogDao.deleteDog(url)
    }

    suspend fun getAllFavoriteDogs(): List<DogCacheEntity> {
        return dogDao.getAllFavoriteDogs()
    }

    suspend fun getDogByUrl(url: String): DogCacheEntity? {
        return dogDao.getDogByUrl(url)
    }

    suspend fun updateDog(dog: DogCacheEntity) {
        dogDao.updateDog(dog)
    }
}
