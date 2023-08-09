package com.example.tmdbkotlinapp.ui.popular_movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.tmdbkotlinapp.databinding.PopularMovieCardBinding
import com.example.tmdbkotlinapp.domain.models.Movie

class PopularMoviesAdapter :
    PagingDataAdapter<Movie, PopularMoviesViewHolder>(PopularMovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val binding =
            PopularMovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class PopularMovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.movieId == newItem.movieId

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
}