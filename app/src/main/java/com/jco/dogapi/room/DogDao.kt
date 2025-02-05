package com.jco.dogapi.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dog: DogCacheEntity)

    @Query("DELETE FROM dog_table WHERE url = :url")
    suspend fun deleteDog(url: String): Int

    @Query("SELECT * FROM dog_table WHERE isFavorite = 1")
    suspend fun getAllFavoriteDogs(): List<DogCacheEntity>

    @Query("SELECT * FROM dog_table WHERE url = :url")
    suspend fun getDogByUrl(url: String): DogCacheEntity?

    @Update
    suspend fun updateDog(dog: DogCacheEntity)

    @Query("DELETE FROM dog_table")
    suspend fun deleteAllDogs(): Int
}