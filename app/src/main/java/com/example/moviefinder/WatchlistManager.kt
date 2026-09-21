package com.example.moviefinder

import android.content.Context
import com.example.moviefinder.model.Movie
import org.json.JSONArray
import org.json.JSONObject

/**
 * Handles saving and retrieving movies from the user's watchlist.
 */
class WatchlistManager(private val context: Context) {

    // SharedPreferences is used to save the watchlist locally
    private val preferences = context.getSharedPreferences(
        "MovieFinderWatchlist",
        Context.MODE_PRIVATE
    )

    // Add a movie to the watchlist
    fun addWatchlist(movie: Movie) {

        val watchlist = getWatchlist().toMutableList()

        // Don't add the same movie twice
        if (watchlist.any { it.id == movie.id }) {
            return
        }

        watchlist.add(movie)

        saveWatchlist(watchlist)
    }

    // Remove a movie from the watchlist
    fun removeWatchlist(movieId: Int) {

        val watchlist = getWatchlist()
            .filter { it.id != movieId }

        saveWatchlist(watchlist)
    }

    // Check whether a movie is already in the watchlist
    fun isInWatchlist(movieId: Int): Boolean {

        return getWatchlist().any {
            it.id == movieId
        }
    }

    // Get all saved watchlist movies
    fun getWatchlist(): List<Movie> {

        val jsonString = preferences.getString(
            "watchlist",
            "[]"
        )

        val jsonArray = JSONArray(jsonString)

        val movies = mutableListOf<Movie>()

        for (i in 0 until jsonArray.length()) {

            val movieObject =
                jsonArray.getJSONObject(i)

            movies.add(
                Movie(
                    id = movieObject.getInt("id"),
                    title = movieObject.getString("title"),
                    overview = movieObject.getString("overview"),

                    poster_path =
                        if (movieObject.isNull("poster_path")) {
                            null
                        } else {
                            movieObject.getString("poster_path")
                        },

                    vote_average =
                        movieObject.getDouble("vote_average"),

                    release_date =
                        if (movieObject.isNull("release_date")) {
                            null
                        } else {
                            movieObject.getString("release_date")
                        }
                )
            )
        }

        return movies
    }

    // Save the watchlist
    private fun saveWatchlist(
        movies: List<Movie>
    ) {

        val jsonArray = JSONArray()

        movies.forEach { movie ->

            val movieObject = JSONObject()

            movieObject.put(
                "id",
                movie.id
            )

            movieObject.put(
                "title",
                movie.title
            )

            movieObject.put(
                "overview",
                movie.overview
            )

            if (movie.poster_path == null) {

                movieObject.put(
                    "poster_path",
                    JSONObject.NULL
                )

            } else {

                movieObject.put(
                    "poster_path",
                    movie.poster_path
                )
            }

            movieObject.put(
                "vote_average",
                movie.vote_average
            )

            if (movie.release_date == null) {

                movieObject.put(
                    "release_date",
                    JSONObject.NULL
                )

            } else {

                movieObject.put(
                    "release_date",
                    movie.release_date
                )
            }

            jsonArray.put(movieObject)
        }

        preferences.edit()
            .putString(
                "watchlist",
                jsonArray.toString()
            )
            .apply()
    }
}