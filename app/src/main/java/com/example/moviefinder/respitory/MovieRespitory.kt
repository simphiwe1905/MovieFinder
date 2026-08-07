package com.example.moviefinder.repository

import com.example.moviefinder.Constants
import com.example.moviefinder.network.RetrofitInstance

class MovieRepository {

    suspend fun getPopularMovies() =
        RetrofitInstance.api.getPopularMovies(Constants.API_KEY)

}