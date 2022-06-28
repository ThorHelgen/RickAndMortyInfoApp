package com.thorhelgen.rickandmortyinfoapp.di

import androidx.lifecycle.ViewModel
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
    abstract fun mainViewModel(viewModel: MainViewModel) : ViewModel
}