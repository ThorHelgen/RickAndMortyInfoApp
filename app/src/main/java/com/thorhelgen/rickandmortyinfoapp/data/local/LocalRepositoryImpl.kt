package com.thorhelgen.rickandmortyinfoapp.data.local

import android.content.Context
import androidx.sqlite.db.SimpleSQLiteQuery
import com.thorhelgen.rickandmortyinfoapp.data.local.entities.CharacterEpisodeCrossRef
import com.thorhelgen.rickandmortyinfoapp.data.local.entities.CharacterWithEpisodes
import com.thorhelgen.rickandmortyinfoapp.data.local.entities.EpisodeWithCharacters
import com.thorhelgen.rickandmortyinfoapp.data.local.entities.LocationWithCharacters
import com.thorhelgen.rickandmortyinfoapp.data.local.mappers.*
import com.thorhelgen.rickandmortyinfoapp.domain.entities.*
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.LocalRepository

class LocalRepositoryImpl constructor(
    private val context: Context,
    private val cacheDB: CacheDB
) : LocalRepository {

    override suspend fun getCharactersPage(pageNumber: Int): List<Character> {
        val mapper = DataToDomainCharacterMapper(context)
        return cacheDB.getCacheDAO().queryCharactersPage(pageNumber).map {
            mapper.map(it)
        }
    }

    override suspend fun cacheCharacters(characters: List<Character>) {
        val mapper = DomainToDataCharacterMapper(context)
        characters.map {
            cacheDB.getCacheDAO().insertCharacter(mapper.map(it))
        }
    }

    override suspend fun getCharacterDetails(characterId: Int): CharacterDetails {
        val characterMapper = DataToDomainCharacterMapper(context)
        val episodeMapper = DataToDomainEpisodeMapper()
        val details: CharacterWithEpisodes = cacheDB
            .getCacheDAO()
            .queryCharacterWithEpisodes(characterId)
        return CharacterDetails(
            characterMapper.map(details.character),
            details.episodes.map {
                episodeMapper.map(it)
            }
        )
    }

    override suspend fun cacheCharacterDetails(characterDetails: CharacterDetails) {
        val episodeMapper = DomainToDataEpisodeMapper()
        characterDetails.episodes.map {
            cacheDB.getCacheDAO().insertCharacterEpisodeCrossRef(
                CharacterEpisodeCrossRef(
                    characterDetails.character.id,
                    it.id
                )
            )
            cacheDB.getCacheDAO().insertEpisode(
                episodeMapper.map(it)
            )
        }
    }

    override suspend fun getLocationsPage(pageNumber: Int): List<Location> {
        val mapper = DataToDomainLocationMapper()
        return cacheDB.getCacheDAO().queryLocationsPage(pageNumber).map {
            mapper.map(it)
        }
    }

    override suspend fun cacheLocations(locations: List<Location>) {
        val mapper = DomainToDataLocationMapper()
        locations.map {
            cacheDB.getCacheDAO().insertLocation(mapper.map(it))
        }
    }

    override suspend fun getLocationDetails(locationId: Int): LocationDetails {
        val locationMapper = DataToDomainLocationMapper()
        val characterMapper = DataToDomainCharacterMapper(context)
        val details: LocationWithCharacters = cacheDB
            .getCacheDAO()
            .queryLocationWithCharacters(locationId)
        return LocationDetails(
            locationMapper.map(details.location),
            details.characters.map {
                characterMapper.map(it)
            }
        )
    }

    override suspend fun cacheLocationDetails(locationDetails: LocationDetails) {
        val characterMapper = DomainToDataCharacterMapper(context)
        locationDetails.residents.map {
            cacheDB.getCacheDAO().insertCharacter(characterMapper.map(it))
        }
    }

    override suspend fun getEpisodesPage(pageNumber: Int): List<Episode> {
        val mapper = DataToDomainEpisodeMapper()
        return cacheDB.getCacheDAO().queryEpisodesPage(pageNumber).map {
            mapper.map(it)
        }
    }

    override suspend fun cacheEpisodes(episodes: List<Episode>) {
        val mapper = DomainToDataEpisodeMapper()
        episodes.map {
            cacheDB.getCacheDAO().insertEpisode(mapper.map(it))
        }
    }

    override suspend fun getEpisodeDetails(episodeId: Int): EpisodeDetails {
        val episodeMapper = DataToDomainEpisodeMapper()
        val characterMapper = DataToDomainCharacterMapper(context)
        val details: EpisodeWithCharacters = cacheDB
            .getCacheDAO()
            .queryEpisodeWithCharacters(episodeId)
        return EpisodeDetails(
            episodeMapper.map(details.episode),
            details.characters.map {
                characterMapper.map(it)
            }
        )
    }

    override suspend fun cacheEpisodeDetails(episodeDetails: EpisodeDetails) {
        val characterMapper = DomainToDataCharacterMapper(context)
        episodeDetails.characters.map {
            cacheDB.getCacheDAO().insertCharacterEpisodeCrossRef(
                CharacterEpisodeCrossRef(
                    it.id,
                    episodeDetails.episode.id
                )
            )
            cacheDB.getCacheDAO().insertCharacter(characterMapper.map(it))
        }
    }

    override suspend fun filterCharacters(
        pageNumber: Int,
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ): List<Character> {
        val mapper = DataToDomainCharacterMapper(context)
        val query = StringBuilder("SELECT * FROM Character WHERE 1=1")
        if (name != "") {
            query.append("AND name = $name")
        }
        if (status != "") {
            query.append("AND status = $status")
        }
        if (species != "") {
            query.append("AND species = $species")
        }
        if (type != "") {
            query.append("AND type = $type")
        }
        if (gender != "") {
            query.append("AND gender = $gender")
        }
        return cacheDB.getCacheDAO().filterCharacters(
            SimpleSQLiteQuery(query.toString())
        ).map { mapper.map(it) }
    }

    override suspend fun filterLocations(
        pageNumber: Int,
        name: String,
        type: String,
        dimension: String
    ): List<Location> {
        val mapper = DataToDomainLocationMapper()
        val query = StringBuilder("SELECT * FROM Location WHERE 1=1")
        if (name != "") {
            query.append("AND name = $name")
        }
        if (type != "") {
            query.append("AND type = $type")
        }
        if (dimension != "") {
            query.append("AND dimension = $dimension")
        }
        return cacheDB.getCacheDAO().filterLocations(
            SimpleSQLiteQuery(query.toString())
        ).map { mapper.map(it) }
    }

    override suspend fun filterEpisodes(
        pageNumber: Int,
        name: String,
        episode: String
    ): List<Episode> {
        val mapper = DataToDomainEpisodeMapper()
        val query = StringBuilder("SELECT * FROM Episode WHERE 1=1")
        if (name != "") {
            query.append("AND name = $name")
        }
        if (episode != "") {
            query.append("AND episode = $episode")
        }
        return cacheDB.getCacheDAO().filterEpisodes(
            SimpleSQLiteQuery(query.toString())
        ).map { mapper.map(it) }
    }
}