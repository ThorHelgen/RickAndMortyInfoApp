package com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thorhelgen.rickandmortyinfoapp.R
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.UseCases
import com.thorhelgen.rickandmortyinfoapp.presentation.grid.GridItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _itemsList = MutableLiveData<List<GridItem>>()
    val itemsList: LiveData<List<GridItem>> = _itemsList

    var selectedMenuItemId: Int = R.id.charactersNavItem

    init {
        _itemsList.value = emptyList()
    }

    fun loadCharactersList(
        pageNumber: Int,
        onLoadingFailed: (message: String) -> Unit = {  }
    ) {
        viewModelScope.launch {
            _itemsList.value = useCases.getCharactersPage(pageNumber).map {
                GridItem(
                    it.id,
                    it.name,
                    it.image,
                    it.species,
                    it.originName,
                    it.locationName
                )
            }.apply {
                if (isEmpty()) {
                    onLoadingFailed("Characters cannot be loaded")
                }
            }
        }
    }

    fun loadLocationsList(
        pageNumber: Int,
        onLoadingFailed: (message: String) -> Unit = {  }
    ) {
        viewModelScope.launch {
            _itemsList.value = useCases.getLocationsPage(pageNumber).map {
                GridItem(
                    it.id,
                    it.name,
                    null,
                    it.type,
                    it.dimension
                )
            }.apply {
                if (isEmpty()) {
                    onLoadingFailed("Locations cannot be loaded")
                }
            }
        }
    }

    fun loadEpisodesList(
        pageNumber: Int,
        onLoadingFailed: (message: String) -> Unit = {  }
    ) {
        viewModelScope.launch {
            _itemsList.value = useCases.getEpisodesPage(pageNumber).map {
                GridItem(
                    it.id,
                    it.name,
                    null,
                    it.episode,
                    it.air_date
                )
            }.apply {
                if (isEmpty()) {
                    onLoadingFailed("Episodes cannot be loaded")
                }
            }
        }

    }
}