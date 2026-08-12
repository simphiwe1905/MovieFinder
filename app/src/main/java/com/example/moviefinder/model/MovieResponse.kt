package com.example.moviefinder.model

/**
 * Data class representing the API response for a movie list request.
 */
data class MovieResponse(
    // The list of movies returned by the API
    val results: List<Movie>
)