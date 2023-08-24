package com.example.tmdbkotlinapp.ui.popular_movies

import android.text.Html
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.databinding.PopularMovieCardBinding
import com.example.tmdbkotlinapp.domain.models.Movie

class PopularMoviesViewHolder(private val binding: PopularMovieCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bind(movie: Movie) {
        binding.moviePoster.load(movie.posterPath) {
            placeholder(R.drawable.ic_placeholder)
            listener(onSuccess = { _, _ ->
                binding.moviePoster.scaleType = ImageView.ScaleType.CENTER_CROP
            })
        }
        binding.movieName.text = movie.originalTitle
        binding.movieReleaseDate.text = Html.fromHtml(
            String.format(
                context.getString(R.string.movie_release_date_format), movie.releaseDate
            ), Html.FROM_HTML_MODE_LEGACY)
        binding.movieRating.text = movie.rating.toString()
    }
}