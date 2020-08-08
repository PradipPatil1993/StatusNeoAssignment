package com.example.statusneoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.statusneoapp.database.Converters

@Entity(tableName = "User")
@TypeConverters(Converters::class)
data class User(val salt: String? = "",
                val gender: String? = "",
                val sha1: String? = "",
                val registered: String? = "",
                val cell: String? = "",
                val picture: String = "",
                val ssn: String? = "",
                val password: String? = "",
                val phone: String? = "",
                val dob: String? = "",
                val name: Name?,
                val location: Location,
                @PrimaryKey
                val email: String = "",
                val username: String? = "",
                val md: String? = "")