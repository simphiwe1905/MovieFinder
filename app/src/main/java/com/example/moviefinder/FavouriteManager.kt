package com.example.moviefinder

import android.content.Context
import com.example.moviefinder.model.Movie
import org.json.JSONArray
import org.json.JSONObject

/**
 * Handles saving and retrieving the user's favourite movies.
 */
class FavouriteManager(private val context: Context) {

    private val preferences = context.getSharedPreferences(
        "MovieFinderFavourites",
        Context.MODE_PRIVATE
    )

    /**
     * Saves a movie to the user's favourites.
     */
    fun addFavourite(movie: Movie) {

        val favourites = getFavourites().toMutableList()

        // Prevent the same movie from being saved twice
        if (favourites.any { it.id == movie.id }) {
            return
        }

        favourites.add(movie)

        saveFavourites(favourites)
    }

    /**
     * Removes a movie from favourites.
     */
    fun removeFavourite(movieId: Int) {

        val favourites = getFavourites()
            .filter { it.id != movieId }

        saveFavourites(favourites)
    }

    /**
     * Checks whether a movie is already a favourite.
     */
    fun isFavourite(movieId: Int): Boolean {

        return getFavourites().any {
            it.id == movieId
        }
    }

    /**
     * Gets all saved favourite movies.
     */
    fun getFavourites(): List<Movie> {

        val jsonString = preferences.getString(
            "favourites",
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

    /**
     * Saves the favourite movie list.
     */
    private fun saveFavourites(
        movies: List<Movie>
    ) {

        val jsonArray = JSONArray()

        movies.forEach { movie ->

            val movieObject = JSONObject()

            movieObject.put("id", movie.id)
            movieObject.put("title", movie.title)
            movieObject.put("overview", movie.overview)

            if (movie.poster_path == null) {
                movieObject.put("poster_path", JSONObject.NULL)
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
                "favourites",
                jsonArray.toString()
            )
            .apply()
    }
}