package com.thorhelgen.rickandmortyinfoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thorhelgen.rickandmortyinfoapp.data.local.entities.Character
import com.thorhelgen.rickandmortyinfoapp.data.local.entities.CharacterEpisodeCrossRef
import com.thorhelgen.rickandmortyinfoapp.data.local.entities.Episode
import com.thorhelgen.rickandmortyinfoapp.data.local.entities.Location

@Database(
    entities = [
        Character::class,
        Location::class,
        Episode::class,
        CharacterEpisodeCrossRef::class
    ],
    version = 1
)
abstract class CacheDB : RoomDatabase() {

    abstract fun getCacheDAO(): CacheDAO
}