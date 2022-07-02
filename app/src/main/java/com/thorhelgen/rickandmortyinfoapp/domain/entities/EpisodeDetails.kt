package com.thorhelgen.rickandmortyinfoapp.domain.entities

data class EpisodeDetails(
    val episode: Episode,
    val characters: List<Character>
)
