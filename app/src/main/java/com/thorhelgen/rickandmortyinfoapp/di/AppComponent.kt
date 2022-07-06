package com.thorhelgen.rickandmortyinfoapp.di

import android.content.Context
import com.thorhelgen.rickandmortyinfoapp.presentation.details.characters.CharacterDetailsFragment
import com.thorhelgen.rickandmortyinfoapp.presentation.details.episodes.EpisodeDetailsFragment
import com.thorhelgen.rickandmortyinfoapp.presentation.details.locations.LocationDetailsFragment
import com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        UseCasesModule::class,
        CacheModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    fun inject(mainFragment: MainFragment)

    fun inject(characterDetailsFragment: CharacterDetailsFragment)

    fun inject(locationDetailsFragment: LocationDetailsFragment)

    fun inject(episodeDetailsFragment: EpisodeDetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }
}