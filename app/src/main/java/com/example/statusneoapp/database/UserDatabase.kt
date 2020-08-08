package com.example.statusneoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.statusneoapp.model.User

/**
 * Room database class for access the database instance
 */
@Database(entities = [(User::class)], version = 1, exportSchema = false)
@TypeConverters(Converters::class)

abstract  class UserDatabase : RoomDatabase(){

    abstract fun userDao(): UserDAO?

    companion object {
        private const val DATABASE_NAME = "userDb"
        private var sInstance: UserDatabase? = null
        @Synchronized
        fun getInstance(context: Context): UserDatabase {
            if (sInstance == null) {
                sInstance = Room
                        .databaseBuilder(context, UserDatabase::class.java, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return sInstance!!
        }
    }

}