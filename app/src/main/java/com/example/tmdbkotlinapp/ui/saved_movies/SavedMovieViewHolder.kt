package com.example.tmdbkotlinapp.ui.saved_movies

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tmdbkotlinapp.databinding.SavedMoviesCardBinding
import com.example.tmdbkotlinapp.domain.models.Movie

class SavedMovieViewHolder(private val binding: SavedMoviesCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie){
            binding.moviePoster.load(movie.posterPath)
            binding.movieName.text = movie.originalTitle
            binding.movieRating.text = movie.rating.toString()
            binding.movieMainGenre.text = movie.genreList?.firstOrNull()?.name ?: ""
            binding.movieReleaseDate.text = movie.releaseDate
        }

}