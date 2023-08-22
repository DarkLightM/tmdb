package com.example.tmdbkotlinapp.ui.popular_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.tmdbkotlinapp.R
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
        getItem(position)?.let { movie ->
            holder.bind(movie)
            holder.itemView.setOnClickListener { view ->
                val bundle = Bundle()
                bundle.putInt("movieId", movie.movieId)
                bundle.putInt("movieRemoteId", movie.movieRemoteId)
                val navController = view.findNavController()
                navController.navigate(
                    R.id.action_popularMoviesFragment_to_movieDetailsFragment, bundle
                )
            }
        }
    }

    class PopularMovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.movieId == newItem.movieId

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
}