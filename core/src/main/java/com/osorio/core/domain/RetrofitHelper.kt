package com.osorio.core.domain

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonblob.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}