package com.example.moviefinder.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefinder.R
import com.example.moviefinder.activity_movie_details
import com.example.moviefinder.model.Movie

/**
 * Adapter for displaying movies in the user's watchlist.
 */
class WatchlistAdapter(
    private var movies: List<Movie>
) : RecyclerView.Adapter<WatchlistAdapter.WatchlistViewHolder>() {

    class WatchlistViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val poster: ImageView =
            itemView.findViewById(R.id.moviePoster)

        val title: TextView =
            itemView.findViewById(R.id.movieTitle)

        val rating: TextView =
            itemView.findViewById(R.id.movieRating)

        val overview: TextView =
            itemView.findViewById(R.id.movieOverview)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WatchlistViewHolder {

        val view = LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.movie_item,
            parent,
            false
        )

        return WatchlistViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: WatchlistViewHolder,
        position: Int
    ) {

        val movie = movies[position]

        // Display movie information
        holder.title.text = movie.title

        holder.rating.text =
            "⭐ ${String.format("%.1f", movie.vote_average)}"

        holder.overview.text = movie.overview

        // Load movie poster
        if (!movie.poster_path.isNullOrEmpty()) {

            val posterUrl =
                "https://image.tmdb.org/t/p/w500${movie.poster_path}"

            Glide.with(holder.itemView.context)
                .load(posterUrl)
                .error(android.R.drawable.ic_menu_report_image)
                .into(holder.poster)
        }

        // Open movie details when clicked
        holder.itemView.setOnClickListener {

            val intent = Intent(
                holder.itemView.context,
                activity_movie_details::class.java
            )

            intent.putExtra("movie_id", movie.id)
            intent.putExtra("movie_title", movie.title)
            intent.putExtra("movie_overview", movie.overview)
            intent.putExtra("movie_poster", movie.poster_path)
            intent.putExtra("movie_rating", movie.vote_average)
            intent.putExtra(
                "movie_release_date",
                movie.release_date
            )

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * Updates the movies displayed by the adapter.
     */
    fun updateMovies(
        newMovies: List<Movie>
    ) {
        movies = newMovies
        notifyDataSetChanged()
    }
}