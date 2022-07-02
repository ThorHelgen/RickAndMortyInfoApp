package com.thorhelgen.rickandmortyinfoapp.di

import com.thorhelgen.rickandmortyinfoapp.domain.usecases.LocalRepository
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.RemoteRepository
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.UseCases
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.UseCasesImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule {

    @Singleton
    @Provides
    fun provideUseCases(
        localRepository: LocalRepository,
        remoteRepository: RemoteRepository
    ): UseCases {
        return UseCasesImpl(localRepository, remoteRepository)
    }
}