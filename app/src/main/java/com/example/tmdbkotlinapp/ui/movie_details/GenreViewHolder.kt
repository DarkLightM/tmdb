package com.example.tmdbkotlinapp.ui.movie_details

import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbkotlinapp.databinding.GenreCardBinding
import com.example.tmdbkotlinapp.domain.models.Genre

class GenreViewHolder(private val binding: GenreCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(genre: Genre) {
        binding.genreName.text = genre.name
    }
}