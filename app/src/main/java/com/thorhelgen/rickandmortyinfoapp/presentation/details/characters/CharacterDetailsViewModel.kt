package com.thorhelgen.rickandmortyinfoapp.presentation.details.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Character
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.UseCases
import com.thorhelgen.rickandmortyinfoapp.presentation.list.ListItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _itemsList = MutableLiveData<List<ListItem>>()
    val itemsList: LiveData<List<ListItem>> = _itemsList

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    init {
        _itemsList.value = emptyList()
    }

    fun loadCharacterDetails(
        characterId: Int,
        onLoadFailed: (message: String) -> Unit = {  }
    ) {
        viewModelScope.launch {
            useCases.getCharacterDetails(characterId)?.let { details ->
                _character.value = details.character
                _itemsList.value = details.episodes.map {
                    ListItem(
                        it.id,
                        it.name,
                        it.episode,
                        it.air_date
                    )
                }
            }
                ?: onLoadFailed("Character is not found")
        }
    }

}