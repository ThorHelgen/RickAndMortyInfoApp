package com.thorhelgen.rickandmortyinfoapp.domain.usecases

import com.thorhelgen.rickandmortyinfoapp.domain.entities.*

interface RemoteRepository {
    suspend fun getCharactersPage(pageNumber: Int): List<Character>
    suspend fun getLocationsPage(pageNumber: Int): List<Location>
    suspend fun getEpisodesPage(pageNumber: Int): List<Episode>

    suspend fun getCharacterDetails(characterId: Int): CharacterDetails?
    suspend fun getLocationDetails(locationId: Int): LocationDetails?
    suspend fun getEpisodeDetails(episodeId: Int): EpisodeDetails?

    suspend fun filterCharacters(
        pageNumber: Int,
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
     ): List<Character>

    suspend fun filterLocations(
        pageNumber:Int,
        name: String,
        type: String,
        dimension: String
    ): List<Location>

    suspend fun filterEpisodes(
        pageNumber: Int,
        name: String,
        episode: String
    ): List<Episode>
}