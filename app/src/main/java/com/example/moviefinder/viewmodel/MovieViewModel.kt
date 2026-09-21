package com.example.moviefinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviefinder.model.Movie
import com.example.moviefinder.repository.MovieRepository
import kotlinx.coroutines.launch
import com.example.moviefinder.model.Genre


/**
 * ViewModel for managing movie data and exposing it to the UI.
 */
class MovieViewModel : ViewModel() {

    // Repository for fetching movie data
    private val repository = MovieRepository()

    // Internal MutableLiveData to hold the list of movies
    private val _movies = MutableLiveData<List<Movie>>()

    // Public LiveData to expose the list of movies
    val movies: LiveData<List<Movie>>
        get() = _movies

    // Internal MutableLiveData to hold the list of genres
    private val _genres =
        MutableLiveData<List<Genre>>()

    // Public LiveData to expose the list of genres
    val genres: LiveData<List<Genre>>
        get() = _genres

    init {
        // Fetch popular movies when the ViewModel starts
        fetchMovies()
    }

    /**
     * Fetches popular movies from TMDB.
     */
    private fun fetchMovies() {

        viewModelScope.launch {

            try {

                val response =
                    repository.getPopularMovies()

                _movies.value =
                    response.results

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }

    /**
     * Searches TMDB for movies matching the user's search query.
     */
    fun searchMovies(query: String) {

        // Don't search if the search box is empty
        if (query.isBlank()) {
            fetchMovies()
            return
        }

        viewModelScope.launch {

            try {

                val response =
                    repository.searchMovies(query)

                _movies.value =
                    response.results

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }
    /**
     * Fetches movie genres from TMDB.
     */
    fun fetchGenres() {

        viewModelScope.launch {

            try {

                val response =
                    repository.getMovieGenres()

                _genres.value =
                    response.genres

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }
    /**
     * Fetches movies belonging to a selected genre.
     */
    fun getMoviesByGenre(genreId: Int) {

        viewModelScope.launch {

            try {

                val response =
                    repository.getMoviesByGenre(genreId)

                _movies.value =
                    response.results

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }
}