package com.thorhelgen.rickandmortyinfoapp.data.local

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.thorhelgen.rickandmortyinfoapp.data.local.entities.*

@Dao
interface CacheDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: Location)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episode: Episode)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterEpisodeCrossRef(
        crossRef: CharacterEpisodeCrossRef
    )

    @Query("SELECT * FROM Character ORDER BY id LIMIT 20 OFFSET :pageNumber*20")
    suspend fun queryCharactersPage(pageNumber: Int): List<Character>
    @Transaction
    @Query("SELECT * FROM Character WHERE id = :characterId")
    suspend fun queryCharacterWithEpisodes(characterId: Int): CharacterWithEpisodes?

    @Query("SELECT * FROM Location ORDER BY id LIMIT 20 OFFSET :pageNumber*20")
    suspend fun queryLocationsPage(pageNumber: Int): List<Location>
    @Transaction
    @Query("SELECT * FROM Location WHERE id = :locationId")
    suspend fun queryLocationWithCharacters(locationId: Int): LocationWithCharacters?

    @Query("SELECT * FROM Episode ORDER BY id LIMIT 20 OFFSET :pageNumber*20")
    suspend fun queryEpisodesPage(pageNumber: Int): List<Episode>
    @Transaction
    @Query("SELECT * FROM Episode WHERE id = :episodeId")
    suspend fun queryEpisodeWithCharacters(episodeId: Int): EpisodeWithCharacters?

    @RawQuery
    suspend fun filterCharacters(query: SupportSQLiteQuery): List<Character>

    @RawQuery
    suspend fun filterLocations(query: SupportSQLiteQuery): List<Location>

    @RawQuery
    suspend fun filterEpisodes(query: SupportSQLiteQuery): List<Episode>
}