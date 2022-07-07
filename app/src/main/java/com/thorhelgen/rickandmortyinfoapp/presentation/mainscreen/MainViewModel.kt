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

    var isRefreshing: Boolean = false

    var currentPage: Int = 0

    init {
        _itemsList.value = emptyList()
    }

    fun loadCharactersList(
        pageNumber: Int,
        onLoadingFailed: (message: String) -> Unit = {  },
        onLoadingSuccess: (message: String) -> Unit = {  },
        forceRemote: Boolean = false
    ) {
        viewModelScope.launch {
            _itemsList.value = _itemsList.value?.plus(
                useCases.getCharactersPage(pageNumber, forceRemote).map {
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
                    } else {
                        onLoadingSuccess("Success")
                    }
                }
            )
        }
    }

    fun loadLocationsList(
        pageNumber: Int,
        onLoadingFailed: (message: String) -> Unit = {  },
        onLoadingSuccess: (message: String) -> Unit = {  },
        forceRemote: Boolean = false
    ) {
        viewModelScope.launch {
            _itemsList.value = _itemsList.value?.plus(
                useCases.getLocationsPage(pageNumber, forceRemote).map {
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
                    } else {
                        onLoadingSuccess("Success")
                    }
                }
            )
        }
    }

    fun loadEpisodesList(
        pageNumber: Int,
        onLoadingFailed: (message: String) -> Unit = {  },
        onLoadingSuccess: (message: String) -> Unit = {  },
        forceRemote: Boolean = false
    ) {
        viewModelScope.launch {
            _itemsList.value = _itemsList.value?.plus(
                useCases.getEpisodesPage(pageNumber, forceRemote).map {
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
                    } else {
                        onLoadingSuccess("Success")
                    }
                }
            )
        }

    }

    fun clearList() {
        _itemsList.value = emptyList()
    }
}