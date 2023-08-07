package com.example.tmdbkotlinapp.ui.popular_movies

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.databinding.PopularMovieCardBinding
import com.example.tmdbkotlinapp.domain.models.Movie

class PopularMoviesAdapter :
    PagingDataAdapter<Movie, PopularMoviesAdapter.ViewHolder>(PopularMovieDiffCallback()) {
    class ViewHolder(private val binding: PopularMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(movie: Movie) {
            Log.i("Adapter", movie.toString())
            binding.moviePoster.load(movie.posterPath)
            binding.movieName.text =
                String.format(context.getString(R.string.movie_name_format), movie.originalTitle)
            binding.movieReleaseDate.text =
                String.format(
                    context.getString(R.string.movie_release_date_format),
                    movie.releaseDate
                )
            binding.movieRating.text =
                String.format(
                    context.getString(R.string.movie_rating_format),
                    movie.rating.toString()
                )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PopularMovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it) }
    }

    class PopularMovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.movieId == newItem.movieId

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
}