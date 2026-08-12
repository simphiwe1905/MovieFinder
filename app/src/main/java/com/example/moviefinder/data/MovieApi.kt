package com.example.moviefinder.data

import com.example.moviefinder.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface defining the API endpoints for fetching movie data.
 */
interface MovieApi {

    /**
     * Fetches the list of popular movies from TMDB.
     * @param apiKey The API key for authentication.
     * @return A MovieResponse object containing the list of popular movies.
     */
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): MovieResponse

}