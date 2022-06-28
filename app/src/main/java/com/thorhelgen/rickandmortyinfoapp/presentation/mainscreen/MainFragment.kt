package com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.thorhelgen.rickandmortyinfoapp.R
import com.thorhelgen.rickandmortyinfoapp.databinding.FragmentMainBinding
import com.thorhelgen.rickandmortyinfoapp.presentation.App.Companion.appComponent
import com.thorhelgen.rickandmortyinfoapp.presentation.ViewModelFactory
import com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen.grid.GridAdapter
import javax.inject.Inject

class MainFragment() : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        appComponent.inject(this)

        val binding = FragmentMainBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.mainBottomNavigation.setOnItemSelectedListener {
            viewModel.selectedMenuItemId = it.itemId
            when(it.itemId) {
                R.id.charactersNavItem -> {
                    binding.itemsGrid.adapter = GridAdapter { id -> /* TODO: implement a transaction to a character fragment */ }
                    viewModel.loadCharactersList()
                    true
                }
                R.id.locationsNavItem -> {
                    binding.itemsGrid.adapter = GridAdapter { id -> /* TODO: implement a transaction to a location fragment */ }
                    viewModel.loadLocationsList()
                    true
                }
                R.id.locationsNavItem -> {
                    binding.itemsGrid.adapter = GridAdapter { id -> /* TODO: implement a transaction to a episode fragment */ }
                    viewModel.loadEpisodesList()
                    true
                }
                else -> false
            }
        }
        binding.mainBottomNavigation.selectedItemId = viewModel.selectedMenuItemId

        return binding.root
    }
}