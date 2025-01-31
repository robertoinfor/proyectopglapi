package com.jco.dogapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jco.dogapi.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(private val dogRepository: DogRepository) : ViewModel() {

    private val _dogImage = MutableLiveData<String>()
    val dogImage: LiveData<String> get() = _dogImage


    fun fetchRandomDogImage() {
        viewModelScope.launch {
            try {
                val response = dogRepository.getRandomDogImage()
                _dogImage.postValue(response.message)
            } catch (e: Exception) {
                // Manejo de errores
            }
        }
    }
}
