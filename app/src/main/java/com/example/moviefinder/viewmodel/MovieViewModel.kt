package com.example.moviefinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviefinder.model.Movie
import com.example.moviefinder.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _movies = MutableLiveData<List<Movie>>()

    val movies: LiveData<List<Movie>>
        get() = _movies


    init {
        fetchMovies()
    }


    private fun fetchMovies() {

        viewModelScope.launch {

            try {

                val response = repository.getPopularMovies()

                _movies.value = response.results

            } catch (e: Exception) {

                e.printStackTrace()

            }

        }
    }
}