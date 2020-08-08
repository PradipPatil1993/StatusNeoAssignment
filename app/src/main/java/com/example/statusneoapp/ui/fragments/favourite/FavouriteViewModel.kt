package com.example.statusneoapp.ui.fragments.favourite

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.statusneoapp.database.UserDatabaseRepository
import com.example.statusneoapp.model.User

class FavouriteViewModel : ViewModel() {

     var userList = MutableLiveData<ArrayList<User>>()

    /**
     * Will fetch the user list from database
     */
    fun fetchAllUsersList(context: Context): MutableLiveData<ArrayList<User>> {
        userList =  UserDatabaseRepository.getAllUsersData(context)
        return userList
    }



}