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
     */
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): MovieResponse


    /**
     * Searches for movies using a movie title.
     */
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): MovieResponse

}