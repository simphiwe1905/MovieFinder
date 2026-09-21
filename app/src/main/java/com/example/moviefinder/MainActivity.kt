package com.example.moviefinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
 * The main activity that displays movies and allows users to search.
 */
class MainActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels()

    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if the user is logged in
        val sharedPreferences = getSharedPreferences(
            "MovieFinderUsers",
            MODE_PRIVATE
        )

        val isLoggedIn = sharedPreferences.getBoolean(
            "isLoggedIn",
            false
        )

        // If the user is not logged in,
        // send them back to the Login screen
        if (!isLoggedIn) {

            val intent = Intent(
                this,
                activity_login::class.java
            )

            startActivity(intent)
            finish()
            return
        }

        enableEdgeToEdge()

        // Connect MainActivity to activity_main.xml
        setContentView(R.layout.activity_main)

        // Handle screen insets
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

            val systemBars =
                insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                )

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        // -----------------------------
        // SETTINGS
        // -----------------------------

        val settingsButton = findViewById<Button>(
            R.id.btnSettings
        )

        settingsButton.setOnClickListener {

            val intent = Intent(
                this,
                activity_settings::class.java
            )

            startActivity(intent)
        }

        val favouritesButton = findViewById<Button>(
            R.id.btnFavourites
        )

        favouritesButton.setOnClickListener {

            val intent = Intent(
                this,
                activity_favourites::class.java
            )

            startActivity(intent)
        }

        // -----------------------------
        // SEARCH
        // -----------------------------

        val searchInput = findViewById<EditText>(
            R.id.edtSearchMovie
        )

        val searchButton = findViewById<Button>(
            R.id.btnSearchMovie
        )

        searchButton.setOnClickListener {

            val searchQuery =
                searchInput.text.toString().trim()

            if (searchQuery.isNotEmpty()) {

                movieViewModel.searchMovies(
                    searchQuery
                )

            } else {

                // If the search box is empty,
                // show the popular movies again
                movieViewModel.searchMovies("")
            }
        }

        movieRecyclerView =
            findViewById(R.id.movieRecyclerView)

        movieRecyclerView.layoutManager =
            LinearLayoutManager(this)

        // Create movie adapter
        movieAdapter = MovieAdapter(emptyList())

        movieRecyclerView.adapter = movieAdapter

        movieViewModel.movies.observe(this) { movies ->

            movieAdapter.updateMovies(movies)

        }
    }
}