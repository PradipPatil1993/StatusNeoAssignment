package com.example.statusneoapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.statusneoapp.model.User

/**
 * DAO class for managing database Queries
 */
@Dao
interface UserDAO {

    @Insert(onConflict = REPLACE)
    fun insertUserData(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>
}