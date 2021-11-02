package com.osorio.dogswelove.framework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osorio.core.data.Dog
import com.osorio.core.domain.DogService
import com.osorio.core.interactors.GetDogs
import kotlinx.coroutines.launch

class DogsViewModel : ViewModel() {
    val isBusy = MutableLiveData<Boolean>()
    val onSuccess = MutableLiveData<Boolean>()
    val dogs = MutableLiveData<List<Dog>>()
    val onErrorMessage = MutableLiveData<String>()

    private val dogService = DogService()

    var getDogs = GetDogs(dogService)

    fun initialize() {
        viewModelScope.launch {
            isBusy.postValue(true)
            val dogsResult = getDogs()

            if (dogsResult.isNullOrEmpty()) {
                onErrorMessage.postValue("Failed to fetch dogs")
                onSuccess.postValue(false)
            } else {
                dogs.postValue(dogsResult)
                onSuccess.postValue(true)
            }

            isBusy.postValue(false)
        }
    }
}