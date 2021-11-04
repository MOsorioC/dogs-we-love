package com.osorio.dogswelove.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DogEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DogsWeLoveDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "dogs_we_love.db"

        private var instance: DogsWeLoveDatabase? = null

        private fun create(context: Context): DogsWeLoveDatabase =
            Room.databaseBuilder(context, DogsWeLoveDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): DogsWeLoveDatabase =
            (instance ?: create(context).also { instance = it })
    }

    abstract fun dogDao(): DogDao
}