package com.example.tmdbkotlinapp.ui.movie_details

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.databinding.ActorCardBinding
import com.example.tmdbkotlinapp.domain.models.Actor

class ActorViewHolder(private val binding: ActorCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(actor: Actor) {
        binding.actorImage.load(actor.image) {
            placeholder(R.drawable.ic_user_placeholder)
        }
        binding.actorName.text = actor.name
    }

}