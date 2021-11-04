package com.osorio.dogswelove.framework

import android.content.Context
import com.osorio.core.data.Dog
import com.osorio.dogswelove.framework.db.DogEntity
import com.osorio.dogswelove.framework.db.DogsWeLoveDatabase

class RoomDogDataSource(private val context: Context) {
    private val dogDao = DogsWeLoveDatabase.getInstance(context).dogDao()

    suspend fun getDogs(): List<Dog> =
        dogDao.getDogs().map { Dog(it.dogName, it.description, it.age, it.image) }

    suspend fun removeDogs() = dogDao.cleanTable()

    suspend fun addDog(dog: Dog) =
        dogDao.addDog(DogEntity(dogName = dog.dogName, description = dog.description,
            age = dog.age, image = dog.image))

}