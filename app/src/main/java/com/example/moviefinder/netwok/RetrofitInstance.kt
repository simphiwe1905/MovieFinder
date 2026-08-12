package com.example.moviefinder.network

import com.example.moviefinder.Constants
import com.example.moviefinder.data.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object to provide a Retrofit instance for network operations.
 */
object RetrofitInstance {

    /**
     * Lazy-initialized API service for Movie-related requests.
     */
    val api: MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}