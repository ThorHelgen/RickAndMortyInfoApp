package com.thorhelgen.rickandmortyinfoapp.di

import androidx.lifecycle.ViewModel
import com.thorhelgen.rickandmortyinfoapp.presentation.details.characters.CharacterDetailsViewModel
import com.thorhelgen.rickandmortyinfoapp.presentation.details.episodes.EpisodeDetailsViewModel
import com.thorhelgen.rickandmortyinfoapp.presentation.details.locations.LocationDetailsViewModel
import com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ClassKey(MainViewModel::class)
    @IntoMap
    abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @ClassKey(CharacterDetailsViewModel::class)
    @IntoMap
    abstract fun characterDetailsViewModel(viewModel: CharacterDetailsViewModel): ViewModel

    @Binds
    @ClassKey(LocationDetailsViewModel::class)
    @IntoMap
    abstract fun locationDetailsViewModel(viewModel: LocationDetailsViewModel): ViewModel

    @Binds
    @ClassKey(EpisodeDetailsViewModel::class)
    @IntoMap
    abstract fun episodeDetailsViewModel(viewModel: EpisodeDetailsViewModel): ViewModel
}