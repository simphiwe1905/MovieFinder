package com.example.moviefinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.R
import com.example.moviefinder.model.Movie

/**
 * Adapter class for the RecyclerView to display a list of movies.
 */
class MovieAdapter(
    private var movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    /**
     * ViewHolder class that holds the views for each movie item.
     */
    class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        // TextView to display the movie title
        val title: TextView =
            itemView.findViewById(R.id.movieTitle)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        // Inflate the layout for a single movie item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        // Bind the movie data to the ViewHolder
        holder.title.text = movies[position].title
    }

    override fun getItemCount(): Int {
        // Return the total number of items in the list
        return movies.size
    }

    /**
     * Updates the movie list and refreshes the RecyclerView.
     */
    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        // Notify the adapter that the data set has changed to refresh the UI
        notifyDataSetChanged()
    }
}