package com.thorhelgen.rickandmortyinfoapp.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class LocationWithCharacters(
    @Embedded val location: Location,
    @Relation(
        parentColumn = "id",
        entityColumn = "locationId",
        entity = Character::class,
    )
    val characters: List<Character>
)
