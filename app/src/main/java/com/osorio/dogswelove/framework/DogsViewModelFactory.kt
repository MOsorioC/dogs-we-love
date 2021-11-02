package com.osorio.dogswelove.framework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DogsViewModelFactory constructor(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DogsViewModel::class.java)) {
            DogsViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}