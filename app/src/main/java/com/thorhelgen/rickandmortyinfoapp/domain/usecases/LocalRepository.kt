package com.thorhelgen.rickandmortyinfoapp.domain.usecases

import com.thorhelgen.rickandmortyinfoapp.domain.entities.*

interface LocalRepository {
    fun getCharactersPage(pageNumber: Int): List<Character>
    fun cacheCharacters(characters: List<Character>)
    fun getCharacterDetails(characterId: Int): CharacterDetails?
    fun cacheCharacterDetails(remoteCharacterDetails: CharacterDetails)

    fun getLocationsPage(pageNumber: Int): List<Location>
    fun cacheLocations(locations: List<Location>)
    fun getLocationDetails(locationId: Int): LocationDetails?
    fun cacheLocationDetails(remoteLocationDetails: LocationDetails)

    fun getEpisodesPage(pageNumber: Int): List<Episode>
    fun cacheEpisodes(remoteEpisodes: List<Episode>)
    fun getEpisodeDetails(episodeId: Int): EpisodeDetails?
    fun cacheEpisodeDetails(remoteEpisodeDetails: EpisodeDetails)

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