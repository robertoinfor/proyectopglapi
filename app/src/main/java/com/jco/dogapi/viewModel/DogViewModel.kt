package com.jco.dogapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jco.dogapi.repository.DogRepository
import com.jco.dogapi.room.DogCacheEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(private val dogRepository: DogRepository) : ViewModel() {

    private val _dogImage = MutableLiveData<String>()
    val dogImage: LiveData<String> get() = _dogImage

    private val _favoriteDogs = MutableLiveData<List<DogCacheEntity>>()
    val favoriteDogs: LiveData<List<DogCacheEntity>> get() = _favoriteDogs


    fun fetchRandomDogImage() {
        viewModelScope.launch {
            try {
                val response = dogRepository.getRandomDogImage()
                val imageUrl = response.message
                _dogImage.postValue(imageUrl)

                val breed = imageUrl.split("/")[4]

                val newDog = DogCacheEntity(url = imageUrl, breed = breed, isFavorite = false)

                dogRepository.insertDog(newDog)
            } catch (e: Exception) {
            }
        }
    }

    fun deleteFavoriteDog(imageUrl: String) {
        viewModelScope.launch {
            try {
                dogRepository.deleteDog(imageUrl)
                getAllFavoriteDogs()
            } catch (e: Exception) {
            }
        }
    }

    fun toggleFavorite(imageUrl: String) {
        viewModelScope.launch {
            val existingDog = dogRepository.getDogByUrl(imageUrl)
            if (existingDog != null) {
                val updatedDog = existingDog.copy(isFavorite = !existingDog.isFavorite)
                dogRepository.updateDog(updatedDog)
            } else {
                val newDog = DogCacheEntity(url = imageUrl, breed = "Unknown", isFavorite = true)
                dogRepository.insertDog(newDog)
            }
        }
    }


    fun getAllFavoriteDogs() {
        viewModelScope.launch {
            val favoriteDogsList = dogRepository.getAllFavoriteDogs()
            _favoriteDogs.postValue(favoriteDogsList)
        }
    }

}
