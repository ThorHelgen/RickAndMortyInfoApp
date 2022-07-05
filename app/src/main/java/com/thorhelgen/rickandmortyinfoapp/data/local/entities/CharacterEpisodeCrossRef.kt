package com.thorhelgen.rickandmortyinfoapp.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["characterId", "episodeId"])
data class CharacterEpisodeCrossRef(
    val characterId: Int,
    val episodeId: Int
)
