package com.thorhelgen.rickandmortyinfoapp.data.local.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class EpisodeWithCharacters(
    @Embedded val episode: Episode,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity = Character::class,
        associateBy = Junction(
            value = CharacterEpisodeCrossRef::class,
            parentColumn = "episodeId",
            entityColumn = "characterId"
        )
    )
    val characters: List<Character>
)
