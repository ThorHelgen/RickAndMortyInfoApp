package com.thorhelgen.rickandmortyinfoapp.presentation.details.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thorhelgen.rickandmortyinfoapp.domain.entities.Episode
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.UseCases
import com.thorhelgen.rickandmortyinfoapp.presentation.grid.GridItem
import com.thorhelgen.rickandmortyinfoapp.presentation.list.ListItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeDetailsViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _itemsList = MutableLiveData<List<GridItem>>()
    val itemsList: LiveData<List<GridItem>> = _itemsList

    private val _episode = MutableLiveData<Episode>()
    val episode: LiveData<Episode> = _episode

    init {
        _itemsList.value = emptyList()
    }

    fun loadEpisodeDetails(
        episodeId: Int,
        onLoadFailed: (message: String) -> Unit = {  }
    ) {
        viewModelScope.launch {
            useCases.getEpisodeDetails(episodeId)?.let { details ->
                _episode.value = details.episode
                _itemsList.value = details.characters.map {
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
                ?: onLoadFailed("Episode is not found")
        }
    }

}