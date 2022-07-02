package com.thorhelgen.rickandmortyinfoapp.domain.usecases

import com.thorhelgen.rickandmortyinfoapp.domain.entities.*

interface LocalRepository {
    suspend fun getCharactersPage(pageNumber: Int): List<Character>
    suspend fun cacheCharacters(characters: List<Character>)
    suspend fun getCharacterDetails(characterId: Int): CharacterDetails?
    suspend fun cacheCharacterDetails(remoteCharacterDetails: CharacterDetails)

    suspend fun getLocationsPage(pageNumber: Int): List<Location>
    suspend fun cacheLocations(locations: List<Location>)
    suspend fun getLocationDetails(locationId: Int): LocationDetails?
    suspend fun cacheLocationDetails(remoteLocationDetails: LocationDetails)

    suspend fun getEpisodesPage(pageNumber: Int): List<Episode>
    suspend fun cacheEpisodes(remoteEpisodes: List<Episode>)
    suspend fun getEpisodeDetails(episodeId: Int): EpisodeDetails?
    suspend fun cacheEpisodeDetails(remoteEpisodeDetails: EpisodeDetails)

    suspend fun filterCharacters(
         name: String,
         status: String,
         species: String,
         type: String,
         gender: String
     ): List<Character>

    suspend fun filterLocations(name: String, type: String, dimension: String): List<Location>

    suspend fun filterEpisodes(name: String, episode: String): List<Episode>
}