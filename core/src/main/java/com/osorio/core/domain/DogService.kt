package com.osorio.core.domain

import com.osorio.core.data.Dog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getDogs(): List<Dog> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(DogDataSource::class.java).getDogs()
            response.body() ?: emptyList()
        }
    }
}