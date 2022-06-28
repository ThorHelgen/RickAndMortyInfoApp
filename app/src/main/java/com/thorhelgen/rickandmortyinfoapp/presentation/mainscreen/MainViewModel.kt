package com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thorhelgen.rickandmortyinfoapp.R
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.UseCases
import com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen.grid.GridItem
import kotlinx.coroutines.async
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