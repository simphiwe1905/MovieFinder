package com.example.moviefinder

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class activity_movie_details : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_details)

        // Find the views from the XML layout
        val poster = findViewById<ImageView>(
            R.id.detailMoviePoster
        )

        val title = findViewById<TextView>(
            R.id.detailMovieTitle
        )

        val rating = findViewById<TextView>(
            R.id.detailMovieRating
        )

        val releaseDate = findViewById<TextView>(
            R.id.detailMovieReleaseDate
        )

        val overview = findViewById<TextView>(
            R.id.detailMovieOverview
        )

        val favouriteButton = findViewById<Button>(
            R.id.btnFavourite
        )

        val watchlistButton = findViewById<Button>(
            R.id.btnWatchlist
        )

        // Get movie information sent from MovieAdapter
        val movieTitle = intent.getStringExtra("movie_title")
        val movieOverview = intent.getStringExtra("movie_overview")
        val moviePoster = intent.getStringExtra("movie_poster")
        val movieRating = intent.getDoubleExtra(
            "movie_rating",
            0.0
        )
        val movieReleaseDate = intent.getStringExtra(
            "movie_release_date"
        )

        // Display movie information
        title.text = movieTitle ?: "Unknown Movie"

        rating.text =
            "⭐ ${String.format("%.1f", movieRating)}"

        releaseDate.text =
            "📅 ${movieReleaseDate ?: "Unknown"}"

        overview.text =
            movieOverview ?: "No overview available."

        // Load the movie poster
        if (!moviePoster.isNullOrEmpty()) {

            val posterUrl =
                "https://image.tmdb.org/t/p/w500$moviePoster"

            Glide.with(this)
                .load(posterUrl)
                .error(android.R.drawable.ic_menu_report_image)
                .into(poster)
        }

        // Favourite button
        favouriteButton.setOnClickListener {

            favouriteButton.text = "❤️ ADDED TO FAVOURITES"
        }

        // Watchlist button
        watchlistButton.setOnClickListener {

            watchlistButton.text = "🔖 ADDED TO WATCHLIST"
        }
    }
}