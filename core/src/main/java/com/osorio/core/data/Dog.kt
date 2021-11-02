package com.osorio.core.data

import com.google.gson.annotations.SerializedName

data class Dog(
    @SerializedName("dogName")
    val dogName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("image")
    val image: String
)