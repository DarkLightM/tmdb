package com.example.tmdbkotlinapp.ui.popular_movies

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.domain.models.Movie

class PopularMoviesAdapter :
    ListAdapter<Movie, PopularMoviesAdapter.ViewHolder>(PopularMovieDiffCallback()) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val defaultImageUrl = "https://image.tmdb.org/t/p/w500%s"
        private val context = view.context

        private val moviePoster = view.findViewById<ImageView>(R.id.moviePoster)
        private val movieName = view.findViewById<TextView>(R.id.movieName)
        private val movieReleaseDate = view.findViewById<TextView>(R.id.movieReleaseDate)
        private val movieRating = view.findViewById<TextView>(R.id.movieRating)

        fun bind(movie: Movie) {
            Log.i("Poster URL", defaultImageUrl.format(movie.posterPath))
            moviePoster.load(defaultImageUrl.format(movie.posterPath))
            movieName.text =
                String.format(context.getString(R.string.movie_name_format), movie.originalTitle)
            movieReleaseDate.text = String.format(
                context.getString(R.string.movie_release_date_format), movie.releaseDate
            )
            movieRating.text = String.format(
                context.getString(R.string.movie_rating_format), movie.rating.toString()
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.popular_movie_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class PopularMovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.movieId == newItem.movieId

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
}