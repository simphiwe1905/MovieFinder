package com.example.moviefinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.adapter.WatchlistAdapter

/**
 * Displays movies saved in the user's watchlist.
 */
class activity_watchlist : AppCompatActivity() {

    private lateinit var watchlistRecyclerView: RecyclerView
    private lateinit var watchlistAdapter: WatchlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_watchlist
        )

        // Connect RecyclerView
        watchlistRecyclerView =
            findViewById(
                R.id.watchlistRecyclerView
            )

        // Set the layout
        watchlistRecyclerView.layoutManager =
            LinearLayoutManager(this)

        // Create adapter
        watchlistAdapter =
            WatchlistAdapter(emptyList())

        // Connect adapter to RecyclerView
        watchlistRecyclerView.adapter =
            watchlistAdapter
    }

    /**
     * Reload the watchlist whenever the screen opens.
     */
    override fun onResume() {
        super.onResume()

        val watchlistManager =
            WatchlistManager(this)

        val watchlist =
            watchlistManager.getWatchlist()

        watchlistAdapter.updateMovies(
            watchlist
        )
    }
}