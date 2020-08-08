package com.example.statusneoapp.ui.fragments.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.statusneoapp.database.UserDatabaseRepository
import com.example.statusneoapp.network.ApiService
import com.example.statusneoapp.model.Results
import com.example.statusneoapp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val apiService = ApiService.create()
   // private  var userList = ArrayList<User>()
    private var userData = MutableLiveData<User>()




    /**
     * Method to load list using API Call
     */
    fun loadUserData() {

        apiService.fetchUser().enqueue(object : Callback<Results?> {
            override fun onFailure(call: Call<Results?>, t: Throwable) {
                userData.value = null
            }

            override fun onResponse(call: Call<Results?>, response: Response<Results?>) {
                if (response.body() != null) {
                    userData.value  = response.body()?.results?.get(0)?.user
                }
            }
        })
    }


    /**
     * This method return instance of {@link userDataList}
     */
    fun getUserData(): MutableLiveData<User> {
        return userData
    }

    /**
     * Will insert the user list from database
     */
    fun insertUser(context: Context,user: User){
        UserDatabaseRepository.insertData(context,user)
    }



}