package com.example.moviefinder

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.adapter.MovieAdapter
import com.example.moviefinder.viewmodel.MovieViewModel

/**
 * The main activity that displays a list of popular movies.
 */
class MainActivity : AppCompatActivity() {

    // ViewModel to manage movie data
    private val movieViewModel: MovieViewModel by viewModels()

    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display for a modern UI
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        // Handle window insets to adjust padding for system bars (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }


        // Initialize RecyclerView to display the list of movies
        movieRecyclerView = findViewById(R.id.movieRecyclerView)

        // Set the layout manager for the RecyclerView
        movieRecyclerView.layoutManager =
            LinearLayoutManager(this)

        // Initialize the adapter with an empty list
        movieAdapter = MovieAdapter(emptyList())

        // Attach the adapter to the RecyclerView
        movieRecyclerView.adapter = movieAdapter

        // Observe the movies LiveData from the ViewModel
        movieViewModel.movies.observe(this) { movies ->
            // Update the adapter when new movie data is available
            movieAdapter.updateMovies(movies)
        }

    }
}