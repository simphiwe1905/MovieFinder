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

class MainActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels()

    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)


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


        // RecyclerView setup
        movieRecyclerView = findViewById(R.id.movieRecyclerView)

        movieRecyclerView.layoutManager =
            LinearLayoutManager(this)


        movieAdapter = MovieAdapter(emptyList())

        movieRecyclerView.adapter = movieAdapter



        // Observe movies from TMDB
        movieViewModel.movies.observe(this) { movies ->

            movieAdapter.updateMovies(movies)

        }

    }
}