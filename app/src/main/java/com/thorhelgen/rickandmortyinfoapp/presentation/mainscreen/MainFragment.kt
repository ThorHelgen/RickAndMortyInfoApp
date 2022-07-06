package com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.thorhelgen.rickandmortyinfoapp.R
import com.thorhelgen.rickandmortyinfoapp.databinding.FragmentMainBinding
import com.thorhelgen.rickandmortyinfoapp.presentation.App.Companion.appComponent
import com.thorhelgen.rickandmortyinfoapp.presentation.ViewModelFactory
import com.thorhelgen.rickandmortyinfoapp.presentation.details.characters.CharacterDetailsFragment
import com.thorhelgen.rickandmortyinfoapp.presentation.details.episodes.EpisodeDetailsFragment
import com.thorhelgen.rickandmortyinfoapp.presentation.details.locations.LocationDetailsFragment
import com.thorhelgen.rickandmortyinfoapp.presentation.grid.GridAdapter
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
                    activity?.title = "Characters"
                    binding.itemsGrid.adapter = GridAdapter { id ->
                        activity?.supportFragmentManager?.commit {
                            val detailsFragment = CharacterDetailsFragment().apply {
                                arguments = Bundle().apply { putInt("id", id) }
                            }
                            replace(R.id.rootFragment, detailsFragment)
                            setReorderingAllowed(true)
                            addToBackStack("to${id}CharacterDetails")
                        }
                    }
                    viewModel.loadCharactersList(0) {
                        Toast.makeText(
                            context,
                            "We cannot load characters. Make sure the Internet connection is available",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    true
                }
                R.id.locationsNavItem -> {
                    activity?.title = "Locations"
                    binding.itemsGrid.adapter = GridAdapter { id ->
                        activity?.supportFragmentManager?.commit {
                            val detailsFragment = LocationDetailsFragment().apply {
                                arguments = Bundle().apply { putInt("id", id) }
                            }
                            replace(R.id.rootFragment, detailsFragment)
                            setReorderingAllowed(true)
                            addToBackStack("to${id}LocationDetails")
                        }
                    }
                    viewModel.loadLocationsList(0) {
                        Toast.makeText(
                            context,
                            "We cannot load locations. Make sure the Internet connection is available",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    true
                }
                R.id.episodesNavItem -> {
                    activity?.title = "Episodes"
                    binding.itemsGrid.adapter = GridAdapter { id ->
                        activity?.supportFragmentManager?.commit {
                            val detailsFragment = EpisodeDetailsFragment().apply {
                                arguments = Bundle().apply { putInt("id", id) }
                            }
                            replace(R.id.rootFragment, detailsFragment)
                            setReorderingAllowed(true)
                            addToBackStack("to${id}EpisodeDetails")
                        }
                    }
                    viewModel.loadEpisodesList(0) {
                        Toast.makeText(
                            context,
                            "We cannot load episodes. Make sure the Internet connection is available",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    true
                }
                else -> false
            }
        }
        binding.mainBottomNavigation.selectedItemId = viewModel.selectedMenuItemId

        return binding.root
    }
}