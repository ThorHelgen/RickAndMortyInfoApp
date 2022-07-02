package com.thorhelgen.rickandmortyinfoapp.domain.usecases

import com.thorhelgen.rickandmortyinfoapp.domain.entities.*

interface RemoteRepository {
    fun getCharactersPage(pageNumber: Int): List<Character>
    fun getLocationsPage(pageNumber: Int): List<Location>
    fun getEpisodesPage(pageNumber: Int): List<Episode>

    fun getCharacterDetails(characterId: Int): CharacterDetails?
    fun getLocationDetails(locationId: Int): LocationDetails?
    fun getEpisodeDetails(episodeId: Int): EpisodeDetails?

    fun filterCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ): List<Character>

    fun filterLocations(name: String, type: String, dimension: String): List<Location>

    fun filterEpisodes(name: String, episode: String): List<Episode>
}