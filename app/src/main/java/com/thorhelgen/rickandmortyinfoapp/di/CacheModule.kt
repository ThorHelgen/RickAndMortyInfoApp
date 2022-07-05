package com.thorhelgen.rickandmortyinfoapp.di

import android.content.Context
import androidx.room.Room
import com.thorhelgen.rickandmortyinfoapp.data.local.CacheDB
import com.thorhelgen.rickandmortyinfoapp.data.local.LocalRepositoryImpl
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.LocalRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun provideCacheDB(context: Context): CacheDB =
        Room.databaseBuilder(
            context,
            CacheDB::class.java,
            "CacheDB"
        ).build()

    @Provides
    fun provideLocalRepository(context: Context, cacheDB: CacheDB): LocalRepository =
        LocalRepositoryImpl(context, cacheDB)
}