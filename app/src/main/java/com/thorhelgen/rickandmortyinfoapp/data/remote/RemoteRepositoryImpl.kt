package com.thorhelgen.rickandmortyinfoapp.data.remote

import android.net.Uri
import com.thorhelgen.rickandmortyinfoapp.data.remote.mappers.CharacterMapper
import com.thorhelgen.rickandmortyinfoapp.data.remote.mappers.EpisodeMapper
import com.thorhelgen.rickandmortyinfoapp.data.remote.mappers.LocationMapper
import com.thorhelgen.rickandmortyinfoapp.domain.entities.*
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.RemoteRepository
import java.lang.Exception

class RemoteRepositoryImpl constructor(
    private val apiService: ApiService
) : RemoteRepository {
    override suspend fun getCharactersPage(pageNumber: Int): List<Character> {
        val mapper = CharacterMapper(apiService)
        val characters = try {
            apiService.getCharactersPage(pageNumber)
        } catch (_: Exception) {
            return emptyList()
        }
        return characters.results.map { mapper.map(it) }
    }

    override suspend fun getLocationsPage(pageNumber: Int): List<Location> {
        val mapper = LocationMapper()
        val locations = try {
            apiService.getLocationsPage(pageNumber)
        } catch (_: Exception) {
            return emptyList()
        }
        return locations.results.map { mapper.map(it) }
    }

    override suspend fun getEpisodesPage(pageNumber: Int): List<Episode> {
        val mapper = EpisodeMapper()
        val episodes = try {
            apiService.getEpisodesPage(pageNumber)
        } catch (_: Exception) {
            return emptyList()
        }
        return episodes.results.map { mapper.map(it) }
    }

    override suspend fun getCharacterDetails(characterId: Int): CharacterDetails? {
        val characterMapper = CharacterMapper(apiService)
        val character = try {
            apiService.getCharacterById(characterId)
        } catch (_: Exception) {
            return null
        }
        val episodes: MutableList<Episode> = mutableListOf()
        character.episode.map {
            val episodesMapper = EpisodeMapper()
            Uri.parse(it).lastPathSegment?.toInt()?.let { id ->
                episodes.add(
                    episodesMapper.map(
                        apiService.getEpisodeById(id)
                    )
                )
            }
        }
        return CharacterDetails(
            characterMapper.map(character),
            episodes
        )
    }

    override suspend fun getLocationDetails(locationId: Int): LocationDetails? {
        val locationMapper = LocationMapper()
        val location = try {
            apiService.getLocationById(locationId)
        } catch (_: Exception) {
            return null
        }
        val residents: MutableList<Character> = mutableListOf()
        location.residents.map {
            val characterMapper = CharacterMapper(apiService)
            Uri.parse(it).lastPathSegment?.toInt()?.let { id ->
                residents.add(
                    characterMapper.map(
                        apiService.getCharacterById(id)
                    )
                )
            }
        }
        return LocationDetails(
            locationMapper.map(location),
            residents
        )
    }

    override suspend fun getEpisodeDetails(episodeId: Int): EpisodeDetails? {
        val episodeMapper = EpisodeMapper()
        val episode = try {
            apiService.getEpisodeById(episodeId)
        } catch (_: Exception) {
            return null
        }
        val characters: MutableList<Character> = mutableListOf()
        episode.characters.map {
            val characterMapper = CharacterMapper(apiService)
            Uri.parse(it).lastPathSegment?.toInt()?.let { id ->
                characters.add(
                    characterMapper.map(
                        apiService.getCharacterById(id)
                    )
                )
            }
        }
        return EpisodeDetails(
            episodeMapper.map(episode),
            characters
        )
    }

    override suspend fun filterCharacters(
        pageNumber: Int,
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ): List<Character> {
        val mapper = CharacterMapper(apiService)
        val characters = try {
            apiService.getCharactersPage(
                pageNumber,
                name,
                status,
                species,
                type,
                gender
            )
        }
         catch (_: Exception) {
             return emptyList()
        }
        return characters.results.map { mapper.map(it) }
    }

    override suspend fun filterLocations(
        pageNumber: Int,
        name: String,
        type: String,
        dimension: String
    ): List<Location> {
        val mapper = LocationMapper()
        val locations = try {
            apiService.getLocationsPage(
                pageNumber,
                name,
                type,
                dimension
            )
        }
        catch (_: Exception) {
            return emptyList()
        }
        return locations.results.map { mapper.map(it) }
    }

    override suspend fun filterEpisodes(
        pageNumber: Int,
        name: String,
        episode: String
    ): List<Episode> {
        val mapper = EpisodeMapper()
        val episodes = try {
            apiService.getEpisodesPage(
                pageNumber,
                name,
                episode
            )
        }
        catch (_: Exception) {
            return emptyList()
        }
        return episodes.results.map { mapper.map(it) }
    }
}