package com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thorhelgen.rickandmortyinfoapp.R
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.UseCasesImpl
import com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen.grid.GridItem
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val useCases: UseCasesImpl
) : ViewModel() {
    private val _itemsList = MutableLiveData<List<GridItem>>()
    val itemsList: LiveData<List<GridItem>> = _itemsList

    var selectedMenuItemId: Int = R.id.charactersNavItem

    init {
        _itemsList.value = emptyList()
    }

    fun loadCharactersList() {
        // TODO: implement loading characters
    }
    fun loadLocationsList() {
        // TODO: implement loading locations
    }
    fun loadEpisodesList() {
        // TODO: implement loading episodes
    }
}