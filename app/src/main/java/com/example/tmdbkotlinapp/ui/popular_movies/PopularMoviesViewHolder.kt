package com.example.tmdbkotlinapp.ui.popular_movies

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.databinding.PopularMovieCardBinding
import com.example.tmdbkotlinapp.domain.models.Movie

class PopularMoviesViewHolder(private val binding: PopularMovieCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bind(movie: Movie) {
        binding.moviePoster.load(movie.posterPath)
        binding.movieName.text =
            String.format(context.getString(R.string.movie_name_format), movie.originalTitle)
        binding.movieReleaseDate.text = String.format(
            context.getString(R.string.movie_release_date_format), movie.releaseDate
        )
        binding.movieRating.text = String.format(
            context.getString(R.string.movie_rating_format), movie.rating.toString()
        )
    }
}