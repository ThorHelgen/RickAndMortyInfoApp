package com.thorhelgen.rickandmortyinfoapp.domain.usecases

import com.thorhelgen.rickandmortyinfoapp.domain.entities.*

interface UseCases {

    suspend fun getCharactersPage(pageNumber: Int, forceRemote: Boolean = false): List<Character>
    suspend fun getCharacterDetails(characterId: Int, forceRemote: Boolean = false): CharacterDetails?

    suspend fun getLocationsPage(pageNumber: Int, forceRemote: Boolean = false): List<Location>
    suspend fun getLocationDetails(locationId: Int, forceRemote: Boolean = false): LocationDetails?

    suspend fun getEpisodesPage(pageNumber: Int, forceRemote: Boolean = false): List<Episode>
    suspend fun getEpisodeDetails(episodeId: Int, forceRemote: Boolean = false): EpisodeDetails?

    suspend fun searchCharacter(pageNumber: Int, name: String): List<Character>
    suspend fun searchLocation(pageNumber: Int, name: String): List<Location>
    suspend fun searchEpisode(pageNumber: Int, name: String): List<Episode>

    suspend fun filterCharacters(
        pageNumber: Int,
        name: String = "",
        status: String = "",
        species: String = "",
        type: String = "",
        gender: String = ""
    ): List<Character>
    suspend fun filterLocations(
        pageNumber: Int,
        name: String = "",
        type: String = "",
        dimension: String = ""
    ): List<Location>
    suspend fun filterEpisodes(
        pageNumber: Int,
        name: String = "",
        episode: String = ""
    ): List<Episode>
}