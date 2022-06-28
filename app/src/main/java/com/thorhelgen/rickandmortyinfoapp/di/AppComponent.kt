package com.thorhelgen.rickandmortyinfoapp.di

import android.content.Context
import com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        UseCasesModule::class
    ]
)
interface AppComponent {

    fun inject(mainFragment: MainFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }
}