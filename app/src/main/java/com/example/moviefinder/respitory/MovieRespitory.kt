package com.example.moviefinder.repository

import com.example.moviefinder.Constants
import com.example.moviefinder.network.RetrofitInstance


/**
 * Repository class that handles data operations for Movies.
 * It acts as a mediator between the UI and TMDB API.
 */
class MovieRepository {

    /**
     * Fetches a list of popular movies from TMDB.
     */
    suspend fun getPopularMovies() =
        RetrofitInstance.api.getPopularMovies(
            Constants.API_KEY
        )

    /**
     * Searches TMDB for movies using a search query.
     */
    suspend fun searchMovies(query: String) =
        RetrofitInstance.api.searchMovies(
            Constants.API_KEY,
            query
        )

    suspend fun getMovieGenres() =
        RetrofitInstance.api.getMovieGenres(
            Constants.API_KEY
        )

    suspend fun getMoviesByGenre(genreId: Int) =
        RetrofitInstance.api.getMoviesByGenre(
            Constants.API_KEY,
            genreId
        )
}