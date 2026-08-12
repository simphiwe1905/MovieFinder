package com.example.moviefinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviefinder.model.Movie
import com.example.moviefinder.repository.MovieRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for managing movie data and exposing it to the UI.
 */
class MovieViewModel : ViewModel() {

    // Repository for fetching movie data
    private val repository = MovieRepository()

    // Internal MutableLiveData to hold the list of movies
    private val _movies = MutableLiveData<List<Movie>>()

    // Public LiveData to expose the list of movies to observers (e.g., Activity)
    val movies: LiveData<List<Movie>>
        get() = _movies

    init {
        // Fetch movies when the ViewModel is initialized
        fetchMovies()
    }

    /**
     * Launches a coroutine to fetch popular movies from the repository.
     */
    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                // Fetch the response from the repository
                val response = repository.getPopularMovies()
                // Update the LiveData with the list of movie results
                _movies.value = response.results
            } catch (e: Exception) {
                // Handle potential errors (e.g., network issues)
                e.printStackTrace()
            }
        }
    }
}