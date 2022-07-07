package com.thorhelgen.rickandmortyinfoapp.domain.usecases

import com.thorhelgen.rickandmortyinfoapp.domain.entities.*


class UseCasesImpl (
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : UseCases {
    override suspend fun getCharactersPage(pageNumber: Int, forceRemote: Boolean): List<Character> {

        if (forceRemote) {
            val remoteCharacters: List<Character> = remoteRepository.getCharactersPage(pageNumber)
            if (remoteCharacters.isNotEmpty()) {
                localRepository.cacheCharacters(remoteCharacters)
            }
            return remoteCharacters
        }

        val localCharacters: List<Character> = localRepository.getCharactersPage(pageNumber)
        if (localCharacters.isEmpty()) {
            val remoteCharacters: List<Character> = remoteRepository.getCharactersPage(pageNumber + 1)
            if (remoteCharacters.isNotEmpty()) {
                localRepository.cacheCharacters(remoteCharacters)
            }
            return remoteCharacters
        }
        return localCharacters
    }

    override suspend fun getCharacterDetails(
        characterId: Int,
        forceRemote: Boolean
    ): CharacterDetails? {

        if (forceRemote) {
            remoteRepository.getCharacterDetails(characterId)?.let {
                localRepository.cacheCharacterDetails(it)
                return it
            }
        }

        val localCharacterDetails: CharacterDetails? = localRepository.getCharacterDetails(characterId)
        if (localCharacterDetails == null || localCharacterDetails.episodes.isEmpty()) {
            remoteRepository.getCharacterDetails(characterId)?.let {
                localRepository.cacheCharacterDetails(it)
                return it
            }
        }
        return localCharacterDetails
    }

    override suspend fun getLocationsPage(pageNumber: Int, forceRemote: Boolean): List<Location> {

        if (forceRemote) {
            val remoteLocations: List<Location> = remoteRepository.getLocationsPage(pageNumber + 1)
            if (remoteLocations.isNotEmpty()) {
                localRepository.cacheLocations(remoteLocations)
            }
            return remoteLocations
        }

        val localLocations: List<Location> = localRepository.getLocationsPage(pageNumber)
        if (localLocations.isEmpty()) {
            val remoteLocations: List<Location> = remoteRepository.getLocationsPage(pageNumber)
            if (remoteLocations.isNotEmpty()) {
                localRepository.cacheLocations(remoteLocations)
            }
            return remoteLocations
        }
        return localLocations
    }

    override suspend fun getLocationDetails(
        locationId: Int,
        forceRemote: Boolean
    ): LocationDetails? {

        if (forceRemote) {
            remoteRepository.getLocationDetails(locationId)?.let {
                localRepository.cacheLocationDetails(it)
                return it
            }
        }

        val localLocationDetails: LocationDetails? = localRepository.getLocationDetails(locationId)
        if (localLocationDetails == null || localLocationDetails.residents.isEmpty()) {
            remoteRepository.getLocationDetails(locationId)?.let {
                localRepository.cacheLocationDetails(it)
                return it
            }
        }
        return localLocationDetails
    }

    override suspend fun getEpisodesPage(pageNumber: Int, forceRemote: Boolean): List<Episode> {

        if (forceRemote) {
            val remoteEpisodes: List<Episode> = remoteRepository.getEpisodesPage(pageNumber + 1)
            if (remoteEpisodes.isNotEmpty()) {
                localRepository.cacheEpisodes(remoteEpisodes)
            }
            return remoteEpisodes
        }

        val localEpisodes: List<Episode> = localRepository.getEpisodesPage(pageNumber)
        if (localEpisodes.isEmpty()) {
            val remoteEpisodes: List<Episode> = remoteRepository.getEpisodesPage(pageNumber)
            if (remoteEpisodes.isNotEmpty()) {
                localRepository.cacheEpisodes(remoteEpisodes)
            }
            return remoteEpisodes
        }
        return localEpisodes
    }

    override suspend fun getEpisodeDetails(episodeId: Int, forceRemote: Boolean): EpisodeDetails? {

        if (forceRemote) {
            remoteRepository.getEpisodeDetails(episodeId)?.let {
                localRepository.cacheEpisodeDetails(it)
                return it
            }
        }

        val localEpisodeDetails: EpisodeDetails? = localRepository.getEpisodeDetails(episodeId)
        if (localEpisodeDetails == null || localEpisodeDetails.characters.isEmpty()) {
            remoteRepository.getEpisodeDetails(episodeId)?.let {
                localRepository.cacheEpisodeDetails(it)
                return it
            }
        }
        return localEpisodeDetails
    }

    override suspend fun searchCharacter(pageNumber: Int, name: String): List<Character> {
        return filterCharacters(pageNumber, name)
    }

    override suspend fun searchLocation(pageNumber: Int, name: String): List<Location> {
        return filterLocations(pageNumber, name)
    }

    override suspend fun searchEpisode(pageNumber: Int, name: String): List<Episode> {
        return filterEpisodes(pageNumber, name)
    }

    override suspend fun filterCharacters(
        pageNumber: Int,
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ): List<Character> {
        val localResult: List<Character> = localRepository.filterCharacters(
            pageNumber,  name, status, species, type, gender
        )
        if (localResult.isEmpty()) {
            val remoteResult: List<Character> = remoteRepository.filterCharacters(
                pageNumber, name, status, species, type, gender
            )
            if (remoteResult.isNotEmpty()) {
                localRepository.cacheCharacters(remoteResult)
            }
            return remoteResult
        }
        return localResult
    }

    override suspend fun filterLocations(
        pageNumber: Int,
        name: String,
        type: String,
        dimension: String
    ): List<Location> {
        val localResult: List<Location> = localRepository.filterLocations(
            pageNumber, name, type, dimension
        )
        if (localResult.isEmpty()) {
            val remoteResult: List<Location> = remoteRepository.filterLocations(
                pageNumber, name, type, dimension
            )
            if (remoteResult.isNotEmpty()) {
                localRepository.cacheLocations(remoteResult)
            }
            return remoteResult
        }
        return localResult
    }

    override suspend fun filterEpisodes(
        pageNumber: Int,
        name: String,
        episode: String
    ): List<Episode> {
        val localResult: List<Episode> = localRepository.filterEpisodes(
            pageNumber, name, episode
        )
        if (localResult.isEmpty()) {
            val remoteResult: List<Episode> = remoteRepository.filterEpisodes(
                pageNumber, name, episode
            )
            if (remoteResult.isNotEmpty()) {
                localRepository.cacheEpisodes(remoteResult)
            }
            return remoteResult
        }
        return localResult
    }
}
