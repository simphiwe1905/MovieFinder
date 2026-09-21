package com.example.moviefinder.model

/**
 * Response returned by the TMDB genre endpoint.
 */
data class GenreResponse(
    val genres: List<Genre>
)