package com.example.moviefinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.adapter.FavouriteAdapter

class activity_favourites : AppCompatActivity() {

    private lateinit var favouritesRecyclerView: RecyclerView
    private lateinit var favouriteAdapter: FavouriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_favourites
        )

        // Find RecyclerView
        favouritesRecyclerView =
            findViewById(
                R.id.favouritesRecyclerView
            )

        // Set RecyclerView layout
        favouritesRecyclerView.layoutManager =
            LinearLayoutManager(this)

        // Create adapter
        favouriteAdapter =
            FavouriteAdapter(emptyList())

        favouritesRecyclerView.adapter =
            favouriteAdapter
    }

    override fun onResume() {
        super.onResume()

        // Get saved favourites
        val favouriteManager =
            FavouriteManager(this)

        val favourites =
            favouriteManager.getFavourites()

        // Update RecyclerView
        favouriteAdapter.updateMovies(
            favourites
        )
    }
}