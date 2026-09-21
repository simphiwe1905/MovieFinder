package com.example.moviefinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.R
import com.example.moviefinder.model.Genre

/**
 * Adapter used to display movie genres.
 */
class GenreAdapter(
    private var genres: List<Genre>,
    private val onGenreClick: (Genre) -> Unit
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    class GenreViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val genreName: TextView =
            itemView.findViewById(R.id.genreName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenreViewHolder {

        val view = LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.genre_item,
            parent,
            false
        )

        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: GenreViewHolder,
        position: Int
    ) {

        val genre = genres[position]

        holder.genreName.text = genre.name

        holder.itemView.setOnClickListener {

            onGenreClick(genre)
        }
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    /**
     * Updates the genres displayed by the adapter.
     */
    fun updateGenres(
        newGenres: List<Genre>
    ) {

        genres = newGenres

        notifyDataSetChanged()
    }
}