package com.example.moviefinder

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.adapter.MovieAdapter
import com.example.moviefinder.viewmodel.MovieViewModel

/**
 * Displays movies belonging to a selected genre.
 */
class activity_genre_movies : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels()

    private lateinit var genreMoviesRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_genre_movies
        )

        // Get the genre ID and name sent from the Genres screen
        val genreId = intent.getIntExtra(
            "genre_id",
            0
        )

        val genreName = intent.getStringExtra(
            "genre_name"
        ) ?: "Movies"

        // Display the selected genre name
        val genreTitle = findViewById<TextView>(
            R.id.txtGenreTitle
        )

        genreTitle.text =
            "🎬 $genreName Movies"

        // Find RecyclerView
        genreMoviesRecyclerView =
            findViewById(
                R.id.genreMoviesRecyclerView
            )

        // Use a vertical movie list
        genreMoviesRecyclerView.layoutManager =
            LinearLayoutManager(this)

        // Create the normal MovieAdapter
        movieAdapter =
            MovieAdapter(emptyList())

        genreMoviesRecyclerView.adapter =
            movieAdapter

        // Ask TMDB for movies in this genre
        movieViewModel.getMoviesByGenre(
            genreId
        )

        // Display the results
        movieViewModel.movies.observe(
            this
        ) { movies ->

            movieAdapter.updateMovies(
                movies
            )
        }
    }
}