package com.example.moviefinder.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefinder.R
import com.example.moviefinder.model.Movie
import android.content.Intent
import com.example.moviefinder.activity_movie_details

/**
 * Adapter class for displaying movies in the RecyclerView.
 */
class MovieAdapter(
    private var movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    /**
     * ViewHolder holds the views for one movie.
     */
    class MovieViewHolder(itemView: View) :
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
    ): MovieViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {

        // Get the current movie
        val movie = movies[position]

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
            intent.putExtra("movie_release_date", movie.release_date)

            holder.itemView.context.startActivity(intent)
        }

        // Display movie information
        holder.title.text = movie.title

        holder.rating.text =
            "⭐ ${String.format("%.1f", movie.vote_average)}"

        holder.overview.text = movie.overview

        // Create the full TMDB poster URL
        val posterUrl =
            "https://image.tmdb.org/t/p/w500${movie.poster_path}"

        // Print the URL to Logcat for testing
        Log.d(
            "MOVIE_POSTER",
            "Poster URL: $posterUrl"
        )

        Log.d("GLIDE_TEST", "Trying to load: $posterUrl")

        Glide.with(holder.itemView.context)
            .load(posterUrl)
            .listener(object : com.bumptech.glide.request.RequestListener<android.graphics.drawable.Drawable> {

                override fun onLoadFailed(
                    e: com.bumptech.glide.load.engine.GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<android.graphics.drawable.Drawable>,
                    isFirstResource: Boolean
                ): Boolean {

                    Log.e(
                        "GLIDE_ERROR",
                        "FAILED: $posterUrl",
                        e
                    )

                    return false
                }

                override fun onResourceReady(
                    resource: android.graphics.drawable.Drawable,
                    model: Any,
                    target: com.bumptech.glide.request.target.Target<android.graphics.drawable.Drawable>,
                    dataSource: com.bumptech.glide.load.DataSource,
                    isFirstResource: Boolean
                ): Boolean {

                    Log.d(
                        "GLIDE_SUCCESS",
                        "LOADED: $posterUrl"
                    )

                    return false
                }
            })
            .error(android.R.drawable.ic_menu_report_image)
            .into(holder.poster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * Updates the movie list.
     */
    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}