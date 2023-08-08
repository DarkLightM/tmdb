package com.example.tmdbkotlinapp.ui.movie_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.tmdbkotlinapp.databinding.ActorCardBinding
import com.example.tmdbkotlinapp.domain.models.Actor

class ActorCardAdapter : ListAdapter<Actor, ActorViewHolder>(ActorDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val binding = ActorCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class ActorDiffCallback : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean =
            oldItem.actorId == newItem.actorId

        override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean =
            oldItem == newItem
    }
}