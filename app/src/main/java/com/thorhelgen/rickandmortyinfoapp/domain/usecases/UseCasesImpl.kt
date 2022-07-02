package com.thorhelgen.rickandmortyinfoapp.domain.usecases

import com.thorhelgen.rickandmortyinfoapp.domain.entities.*


class UseCasesImpl (
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : UseCases {
    override suspend fun getCharactersPage(pageNumber: Int): List<Character> {
        val localCharacters: List<Character> = localRepository.getCharactersPage(pageNumber)
        if (localCharacters.isEmpty()) {
            val remoteCharacters: List<Character> = remoteRepository.getCharactersPage(pageNumber)
            if (remoteCharacters.isNotEmpty()) {
                localRepository.cacheCharacters(remoteCharacters)
            }
            return remoteCharacters
        }
        return localCharacters
    }

    override suspend fun getCharacterDetails(characterId: Int): CharacterDetails? {
        val localCharacterDetails: CharacterDetails? = localRepository.getCharacterDetails(characterId)
        if (localCharacterDetails == null || localCharacterDetails.episodes.isEmpty()) {
            val remoteCharacterDetails: CharacterDetails? = remoteRepository.getCharacterDetails(characterId)
            if (remoteCharacterDetails != null) {
                localRepository.cacheCharacterDetails(remoteCharacterDetails)
            }
            return remoteCharacterDetails
        }
        return localCharacterDetails
    }

    override suspend fun getLocationsPage(pageNumber: Int): List<Location> {
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

    override suspend fun getLocationDetails(locationId: Int): LocationDetails? {
        val localLocationDetails: LocationDetails? = localRepository.getLocationDetails(locationId)
        if (localLocationDetails == null || localLocationDetails.residents.isEmpty()) {
            val remoteLocationDetails: LocationDetails? = remoteRepository.getLocationDetails(locationId)
            if (remoteLocationDetails != null) {
                localRepository.cacheLocationDetails(remoteLocationDetails)
            }
            return remoteLocationDetails
        }
        return localLocationDetails
    }

    override suspend fun getEpisodesPage(pageNumber: Int): List<Episode> {
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

    override suspend fun getEpisodeDetails(episodeId: Int): EpisodeDetails? {
        val localEpisodeDetails: EpisodeDetails? = localRepository.getEpisodeDetails(episodeId)
        if (localEpisodeDetails == null || localEpisodeDetails.characters.isEmpty()) {
            val remoteEpisodeDetails: EpisodeDetails? = remoteRepository.getEpisodeDetails(episodeId)
            if (remoteEpisodeDetails != null) {
                localRepository.cacheEpisodeDetails(remoteEpisodeDetails)
            }
            return remoteEpisodeDetails
        }
        return localEpisodeDetails
    }

    override suspend fun searchCharacter(name: String): List<Character> {
        return filterCharacters(name)
    }

    override suspend fun searchLocation(name: String): List<Location> {
        return filterLocations(name)
    }

    override suspend fun searchEpisode(name: String): List<Episode> {
        return filterEpisodes(name)
    }

    override suspend fun filterCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ): List<Character> {
        val localResult: List<Character> = localRepository.filterCharacters(
            name, status, species, type, gender
        )
        if (localResult.isEmpty()) {
            val remoteResult: List<Character> = remoteRepository.filterCharacters(
                name, status, species, type, gender
            )
            if (remoteResult.isNotEmpty()) {
                localRepository.cacheCharacters(remoteResult)
            }
            return remoteResult
        }
        return localResult
    }

    override suspend fun filterLocations(
        name: String,
        type: String,
        dimension: String
    ): List<Location> {
        val localResult: List<Location> = localRepository.filterLocations(
            name, type, dimension
        )
        if (localResult.isEmpty()) {
            val remoteResult: List<Location> = remoteRepository.filterLocations(
                name, type, dimension
            )
            if (remoteResult.isNotEmpty()) {
                localRepository.cacheLocations(remoteResult)
            }
            return remoteResult
        }
        return localResult
    }

    override suspend fun filterEpisodes(name: String, episode: String): List<Episode> {
        val localResult: List<Episode> = localRepository.filterEpisodes(
            name, episode
        )
        if (localResult.isEmpty()) {
            val remoteResult: List<Episode> = remoteRepository.filterEpisodes(
                name, episode
            )
            if (remoteResult.isNotEmpty()) {
                localRepository.cacheEpisodes(remoteResult)
            }
            return remoteResult
        }
        return localResult
    }
}
