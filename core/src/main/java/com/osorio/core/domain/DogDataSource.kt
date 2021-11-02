package com.osorio.core.domain

import com.osorio.core.data.Dog
import com.osorio.core.data.DogsResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogDataSource {
    @GET("880188946124021760")
    suspend fun getDogs() : Response<List<Dog>>
}