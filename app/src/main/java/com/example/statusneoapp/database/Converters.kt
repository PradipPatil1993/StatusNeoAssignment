package com.example.statusneoapp.database

import androidx.room.TypeConverter
import com.example.statusneoapp.model.Location
import com.example.statusneoapp.model.Name
import com.google.gson.Gson

/**
 * Converters class for database to convert custom data-types
 */
class Converters {

    @TypeConverter
    fun userToJson(value: Location?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToUser(value: String) = Gson().fromJson(value, Location::class.java)

    @TypeConverter
    fun nameToJson(value: Name?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToName(value: String) = Gson().fromJson(value, Name::class.java)
}