package com.jco.dogapi.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_table")
data class DogCacheEntity(@PrimaryKey
                          @ColumnInfo(name = "url") val url: String,
                          @ColumnInfo(name = "breed") val breed: String,
                          @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false
)