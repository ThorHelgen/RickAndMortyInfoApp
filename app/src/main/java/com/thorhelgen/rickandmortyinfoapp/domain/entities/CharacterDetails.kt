package com.thorhelgen.rickandmortyinfoapp.domain.entities

data class CharacterDetails(
    val character: Character?,
    val episodes: List<Episode>
)
