package com.example.moviefinder.model

/**
 * Data class representing a Movie item from the TMDB API.
 */
data class Movie(
    // Unique identifier for the movie
    val id: Int,
    // Title of the movie
    val title: String,
    // Brief summary of the movie
    val overview: String,
    // Path to the movie's poster image
    val poster_path: String?,
    // Average rating of the movie
    val vote_average: Double
)