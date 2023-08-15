package com.example.tmdbkotlinapp.ui.saved_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.data.repository.DataSource
import com.example.tmdbkotlinapp.databinding.SavedMoviesCardBinding
import com.example.tmdbkotlinapp.domain.models.Movie

class SavedMoviesAdapter : ListAdapter<Movie, SavedMovieViewHolder>(SavedMovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedMovieViewHolder {
        val binding =
            SavedMoviesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedMovieViewHolder, position: Int) {
        holder.bind(currentList[position])
        val movie = currentList[position]
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("movieId", movie.movieId)
            bundle.putSerializable("source", DataSource.LOCAL)
            val navController = it.findNavController()
            navController.navigate(R.id.action_savedMoviesFragment_to_movieDetailsFragment, bundle)
        }
    }

    class SavedMovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.movieId == newItem.movieId

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
}