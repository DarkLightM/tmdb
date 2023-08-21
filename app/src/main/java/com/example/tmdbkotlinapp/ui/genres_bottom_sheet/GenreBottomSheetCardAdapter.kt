package com.example.tmdbkotlinapp.ui.genres_bottom_sheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.tmdbkotlinapp.databinding.GenreCardBinding
import com.example.tmdbkotlinapp.domain.models.Genre
import com.example.tmdbkotlinapp.ui.movie_details.GenreViewHolder

class GenreBottomSheetCardAdapter(private val onItemClick: (String) -> Unit) :
    ListAdapter<Genre, GenreViewHolder>(GenreDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = GenreCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = getItem(position)
        holder.bind(genre)
        holder.itemView.setOnClickListener {
            onItemClick(genre.name)
        }
    }

    class GenreDiffCallback : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean =
            oldItem == newItem
    }
}