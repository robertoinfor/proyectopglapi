package com.jco.dogapi.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDog(dog: DogCacheEntity)

    @Query("DELETE FROM dog_table WHERE url = :url")
    fun deleteDog(url: String)

    @Query("SELECT * FROM dog_table WHERE isFavorite = 1")
    fun getAllFavoriteDogs(): List<DogCacheEntity>

    @Query("SELECT * FROM dog_table WHERE url = :url")
    fun getDogByUrl(url: String): DogCacheEntity?

    @Update
    fun updateDog(dog: DogCacheEntity)

    @Query("DELETE FROM dog_table")
    fun deleteAllDogs()
}