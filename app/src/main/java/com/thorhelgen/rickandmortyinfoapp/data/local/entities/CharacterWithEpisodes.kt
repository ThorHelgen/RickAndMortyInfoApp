package com.thorhelgen.rickandmortyinfoapp.data.local.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CharacterWithEpisodes(
    @Embedded val character: Character,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity = Episode::class,
        associateBy = Junction(
            value = CharacterEpisodeCrossRef::class,
            parentColumn = "characterId",
            entityColumn = "episodeId"
        )
    )
    val episodes: List<Episode>
)
