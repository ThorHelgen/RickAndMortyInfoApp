package com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
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
    ): View {

        appComponent.inject(this)

        val binding = FragmentMainBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.mainBottomNavigation.setOnItemSelectedListener {

            viewModel.selectedMenuItemId = it.itemId

            when(it.itemId) {

                R.id.charactersNavItem -> {

                    activity?.title = "Characters"

                    viewModel.currentPage = 0

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
                    binding.itemsGrid.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            if (!binding.itemsGrid.canScrollVertically(1)) {
                                binding.loadingProgress.visibility = View.VISIBLE
                                viewModel.loadCharactersList(
                                    ++viewModel.currentPage,
                                    {
                                        viewModel.isRefreshing = false
                                    },
                                    {
                                        binding.loadingProgress.visibility = View.INVISIBLE
                                    },
                                    viewModel.isRefreshing
                                )
                            }
                        }
                    })

                    viewModel.clearList()
                    viewModel.loadCharactersList(
                        0,
                        {
                            Toast.makeText(
                                context,
                                "We cannot load characters. Make sure the Internet connection is available",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )

                    viewModel.isRefreshing = false
                    binding.gridWrapper.setOnRefreshListener {
                        if (!hasInternetAccess()) {
                            Toast.makeText(
                                context,
                                "We cannot load characters. Make sure the Internet connection is available",
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.gridWrapper.isRefreshing = false
                            return@setOnRefreshListener
                        }

                        viewModel.clearList()
                        viewModel.loadCharactersList(
                            0,
                            {
                                Toast.makeText(
                                    context,
                                    "We cannot load characters. Make sure the Internet connection is available",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            {
                                binding.gridWrapper.isRefreshing = false
                            },
                            true
                        )

                        viewModel.isRefreshing = true
                    }

                    true
                }

                R.id.locationsNavItem -> {

                    activity?.title = "Locations"

                    viewModel.currentPage = 0

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
                    binding.itemsGrid.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            if (!binding.itemsGrid.canScrollVertically(1)) {
                                binding.loadingProgress.visibility = View.VISIBLE
                                viewModel.loadLocationsList(
                                    ++viewModel.currentPage,
                                    {
                                        viewModel.isRefreshing = false
                                    },
                                    {
                                        binding.loadingProgress.visibility = View.INVISIBLE
                                    },
                                    viewModel.isRefreshing
                                )
                            }
                        }
                    })

                    viewModel.clearList()
                    viewModel.loadLocationsList(
                        0,
                        {
                            Toast.makeText(
                                context,
                                "We cannot load locations. Make sure the Internet connection is available",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )

                    viewModel.isRefreshing = false
                    binding.gridWrapper.setOnRefreshListener {
                        if (!hasInternetAccess()) {
                            Toast.makeText(
                                context,
                                "We cannot load characters. Make sure the Internet connection is available",
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.gridWrapper.isRefreshing = false
                            return@setOnRefreshListener
                        }

                        viewModel.clearList()
                        viewModel.loadLocationsList(
                            0,
                            {
                                Toast.makeText(
                                    context,
                                    "We cannot load locations. Make sure the Internet connection is available",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            {
                                binding.gridWrapper.isRefreshing = false
                            },
                            true
                        )

                        viewModel.isRefreshing = true
                    }

                    true
                }

                R.id.episodesNavItem -> {

                    activity?.title = "Episodes"

                    viewModel.currentPage = 0

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
                    binding.itemsGrid.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            if (!binding.itemsGrid.canScrollVertically(1)) {
                                binding.loadingProgress.visibility = View.VISIBLE
                                viewModel.loadEpisodesList(
                                    ++viewModel.currentPage,
                                    {
                                        viewModel.isRefreshing = false
                                    },
                                    {
                                        binding.loadingProgress.visibility = View.INVISIBLE
                                    },
                                    viewModel.isRefreshing
                                )
                            }
                        }
                    })

                    viewModel.clearList()
                    viewModel.loadEpisodesList(
                        0,
                        {
                            Toast.makeText(
                                context,
                                "We cannot load episodes. Make sure the Internet connection is available",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )

                    viewModel.isRefreshing = false
                    binding.gridWrapper.setOnRefreshListener {
                        if (!hasInternetAccess()) {
                            Toast.makeText(
                                context,
                                "We cannot load characters. Make sure the Internet connection is available",
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.gridWrapper.isRefreshing = false
                            return@setOnRefreshListener
                        }

                        viewModel.clearList()
                        viewModel.loadEpisodesList(
                            0,
                            {
                                Toast.makeText(
                                    context,
                                    "We cannot load episodes. Make sure the Internet connection is available",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            {
                                binding.gridWrapper.isRefreshing = false
                            },
                            true
                        )

                        viewModel.isRefreshing = true
                    }

                    true
                }
                else -> false
            }
        }

        binding.mainBottomNavigation.selectedItemId = viewModel.selectedMenuItemId

        return binding.root
    }

    private fun hasInternetAccess(): Boolean =
        (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo?.isConnected ?: false
}