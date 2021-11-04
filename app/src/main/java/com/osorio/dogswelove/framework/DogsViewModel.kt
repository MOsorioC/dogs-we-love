package com.osorio.dogswelove.framework

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osorio.core.data.Dog
import com.osorio.core.domain.DogService
import com.osorio.core.interactors.GetDogs
import kotlinx.coroutines.launch

class DogsViewModel(private val application: Application) : ViewModel() {
    private val TAG = "DogsViewModel"

    val isBusy = MutableLiveData<Boolean>()
    val onSuccess = MutableLiveData<Boolean>()
    val dogs = MutableLiveData<List<Dog>>()
    val onErrorMessage = MutableLiveData<String>()

    private val dogService = DogService()
    var getDogs = GetDogs(dogService)

    private val dogRepository = RoomDogDataSource(application.applicationContext)

    fun initialize() {
        viewModelScope.launch {
            isBusy.postValue(true)

            val locallyDogs = dogRepository.getDogs()
            if (locallyDogs.isNullOrEmpty()) {
                Log.d(TAG, "initialize: get dogs from API")
                getDogsFromAPI()
            } else {
                Log.d(TAG, "initialize: get locally dogs")
                dogs.postValue(locallyDogs)
                onSuccess.postValue(true)
            }

            isBusy.postValue(false)
        }
    }

    private suspend fun getDogsFromAPI() {
        val dogsResult = getDogs()

        if (dogsResult.isNullOrEmpty()) {
            onErrorMessage.postValue("Failed to fetch dogs")
            onSuccess.postValue(false)
        } else {
            dogs.postValue(dogsResult)
            onSuccess.postValue(true)
            // Clean dogs repository
            dogRepository.removeDogs()
            // Save dogs locally
            dogsResult.forEach { dogRepository.addDog(it) }
        }
    }
}