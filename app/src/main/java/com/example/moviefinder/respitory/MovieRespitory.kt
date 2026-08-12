package com.example.moviefinder.repository

import com.example.moviefinder.Constants
import com.example.moviefinder.network.RetrofitInstance

/**
 * Repository class that handles data operations for Movies.
 * It acts as a mediator between different data sources (in this case, only TMDB API).
 */
class MovieRepository {

    /**
     * Fetches a list of popular movies from the TMDB API.
     * @return The response from the API containing a list of movies.
     */
    suspend fun getPopularMovies() =
        RetrofitInstance.api.getPopularMovies(Constants.API_KEY)

}