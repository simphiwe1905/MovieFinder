
package com.example.moviefinder

import android.content.Context
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
import com.google.firebase.messaging.FirebaseMessaging



/*
 * MainActivity displays movies and allows users to search.
 * Language preferences are applied when the activity starts.
 */

class MainActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels()

    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    // Apply the user's preferred language before loading the screen.
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
           LanguageManager.applyLanguage(newBase)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            // Ask for Notification Permission for Android 13+
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                if (androidx.core.content.ContextCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.POST_NOTIFICATIONS
                    ) != android.content.pm.PackageManager.PERMISSION_GRANTED
                ) {
                    androidx.core.app.ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                        101
                    )
                }
            }

            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    println("TOKEN ERROR: ${task.exception}")
                    return@addOnCompleteListener
                }
                android.util.Log.d("MY_TEST", "MY_APP_TOKEN: ${task.result}")
            }

            // Check if the user is logged in.
            val sharedPreferences = getSharedPreferences(
                "MovieFinderUsers",
                MODE_PRIVATE
            )

            val isLoggedIn = sharedPreferences.getBoolean(
                "isLoggedIn",
                false
            )
        android.util.Log.d("LOGIN_TEST", "App opened. Is logged in? -> $isLoggedIn")

            // If the user is not logged in, redirect to Login.
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

            // Connect MainActivity to activity_main.xml.
            setContentView(R.layout.activity_main)

            // Handle screen insets.
            ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main)
            ) { v, insets ->

                val systemBars = insets.getInsets(
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

            // SETTINGS
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

            // FAVOURITES
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

            // WATCHLIST
            val watchlistButton = findViewById<Button>(
                R.id.btnWatchlist
            )

            watchlistButton.setOnClickListener {
                val intent = Intent(
                    this,
                    activity_watchlist::class.java
                )

                startActivity(intent)
            }

            // GENRES
            val genresButton = findViewById<Button>(
                R.id.btnGenres
            )

            genresButton.setOnClickListener {
                val intent = Intent(
                    this,
                    activity_genres::class.java
                )

                startActivity(intent)
            }

            // SEARCH
            val searchInput = findViewById<EditText>(
                R.id.edtSearchMovie
            )

            val searchButton = findViewById<Button>(
                R.id.btnSearchMovie
            )

            searchButton.setOnClickListener {

                val searchQuery = searchInput.text.toString().trim()

                if (searchQuery.isNotEmpty()) {

                    movieViewModel.searchMovies(
                        searchQuery
                    )

                } else {

                    // Show popular movies when the search box is empty.
                    movieViewModel.searchMovies("")
                }
            }

            // MOVIE RECYCLER VIEW
            movieRecyclerView = findViewById(
                R.id.movieRecyclerView
            )

            movieRecyclerView.layoutManager =
                LinearLayoutManager(this)

            // Create movie adapter.
            movieAdapter = MovieAdapter(emptyList())

            movieRecyclerView.adapter = movieAdapter

            // Observe movie updates.
            movieViewModel.movies.observe(this) { movies ->

                movieAdapter.updateMovies(movies)

            }
    }
}
