package com.example.moviefinder

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.adapter.GenreAdapter
import com.example.moviefinder.viewmodel.MovieViewModel

/**
 * Displays all available movie genres.
 */
class activity_genres : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels()

    private lateinit var genresRecyclerView: RecyclerView
    private lateinit var genreAdapter: GenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_genres
        )

        genresRecyclerView =
            findViewById(
                R.id.genresRecyclerView
            )

        genresRecyclerView.layoutManager =
            LinearLayoutManager(this)

        // Create the genre adapter
        genreAdapter = GenreAdapter(
            emptyList()
        ) { genre ->

            // Open movies for the selected genre
            val intent = Intent(
                this,
                activity_genre_movies::class.java
            )

            // Send the genre ID to the next screen
            intent.putExtra(
                "genre_id",
                genre.id
            )

            // Send the genre name to the next screen
            intent.putExtra(
                "genre_name",
                genre.name
            )

            startActivity(intent)
        }

        genresRecyclerView.adapter =
            genreAdapter

        // Ask TMDB for the available genres
        movieViewModel.fetchGenres()

        // Observe the genres
        movieViewModel.genres.observe(
            this
        ) { genres ->

            genreAdapter.updateGenres(
                genres
            )
        }
    }
}