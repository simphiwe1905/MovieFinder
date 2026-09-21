package com.example.moviefinder.model

/**
 * Data class representing a Movie item from the TMDB API.
 */
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val vote_average: Double,
    val release_date: String?
)