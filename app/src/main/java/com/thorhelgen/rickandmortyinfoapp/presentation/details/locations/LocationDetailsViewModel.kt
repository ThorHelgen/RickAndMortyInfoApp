package com.thorhelgen.rickandmortyinfoapp.presentation.details.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Location
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.UseCases
import com.thorhelgen.rickandmortyinfoapp.presentation.grid.GridItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationDetailsViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _itemsList = MutableLiveData<List<GridItem>>()
    val itemsList: LiveData<List<GridItem>> = _itemsList

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location

    init {
        _itemsList.value = emptyList()
    }

    fun loadLocationDetails(
        locationId: Int,
        onLoadFailed: (message: String) -> Unit = {  }
    ) {
        viewModelScope.launch {
            useCases.getLocationDetails(locationId)?.let { details ->
                _location.value = details.location
                _itemsList.value = details.residents.map {
                    GridItem(
                        it.id,
                        it.name,
                        it.image,
                        it.species,
                        it.originName,
                        it.locationName
                    )
                }
            }
                ?: onLoadFailed("Location is not found")
        }
    }

}