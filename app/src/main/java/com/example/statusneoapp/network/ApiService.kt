package com.example.statusneoapp.network


import com.example.statusneoapp.model.Results
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Api service class for Access Web APIs
 */
interface ApiService {

    @GET("api/0.4/?randomapi")
    fun fetchUser(): Call<Results>

    /**
     * Get the instance of ApiService class
     */
    companion object Factory {
        private const val BASE_URL: String = "https://randomuser.me/"
        private var gson = GsonBuilder().setLenient().create()

        private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val loggerClient = OkHttpClient.Builder().addInterceptor(logging).build()

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(loggerClient)
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}