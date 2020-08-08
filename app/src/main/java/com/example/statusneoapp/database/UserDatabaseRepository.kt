package com.example.statusneoapp.database

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.statusneoapp.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * User repository for Managing Database transactions
 */
class UserDatabaseRepository {

    companion object {

        lateinit var userDatabase: UserDatabase
        var userList = MutableLiveData<ArrayList<User>>()

        fun initialiseUserDatabase(context: Context): UserDatabase {
            return UserDatabase.getInstance(context)
        }

        fun insertData(context: Context, user: User) {
            userDatabase = initialiseUserDatabase(context)

            CoroutineScope(Dispatchers.IO).launch {
                userDatabase.userDao()?.insertUserData(user)
            }

        }

        fun getAllUsersData(context: Context): MutableLiveData<ArrayList<User>> {
            userDatabase = initialiseUserDatabase(context)
            userList.value = userDatabase.userDao()?.getAllUsers() as ArrayList<User>?
            return userList
        }
    }
}