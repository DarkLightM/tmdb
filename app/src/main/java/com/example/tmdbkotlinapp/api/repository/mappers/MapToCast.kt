package com.example.tmdbkotlinapp.api.repository.mappers

import com.example.tmdbkotlinapp.models.actor.Actor
import com.example.tmdbkotlinapp.models.actor.ActorDataModel

class MapToCast {
    companion object {
        fun mapToCast(actorDataModel: ActorDataModel): List<Actor> {
            return actorDataModel.cast.map {
                Actor(
                    actorId = it.actorId,
                    name = it.name,
                    image = it.image,
                )
            }
        }
    }
}