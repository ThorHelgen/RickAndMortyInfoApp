package com.thorhelgen.rickandmortyinfoapp.di

import com.thorhelgen.rickandmortyinfoapp.domain.usecases.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun provideUseCases(): UseCases =
        UseCases(
            ""
        )
}