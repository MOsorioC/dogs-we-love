package com.osorio.dogswelove.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface DogDao {
    @Insert(onConflict = REPLACE)
    suspend fun addDog(dog: DogEntity)

    @Query("SELECT * FROM dog")
    suspend fun getDogs(): List<DogEntity>

    @Delete
    suspend fun removeDog(dog: DogEntity)

    @Query("DELETE FROM dog")
    suspend fun cleanTable()
}