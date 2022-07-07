package com.thorhelgen.rickandmortyinfoapp.presentation.details.characters

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.thorhelgen.rickandmortyinfoapp.R
import com.thorhelgen.rickandmortyinfoapp.databinding.FragmentCharacterDetailsBinding
import com.thorhelgen.rickandmortyinfoapp.presentation.App.Companion.appComponent
import com.thorhelgen.rickandmortyinfoapp.presentation.MainActivity
import com.thorhelgen.rickandmortyinfoapp.presentation.ViewModelFactory
import com.thorhelgen.rickandmortyinfoapp.presentation.details.episodes.EpisodeDetailsFragment
import com.thorhelgen.rickandmortyinfoapp.presentation.list.ListAdapter
import javax.inject.Inject

class CharacterDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CharacterDetailsViewModel by viewModels({requireActivity()}) { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        appComponent.inject(this)

        activity?.title = "Character Details"

        val binding = FragmentCharacterDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.episodesList.adapter = ListAdapter { id ->
            activity?.supportFragmentManager?.commit {
                val detailsFragment = EpisodeDetailsFragment().apply {
                    arguments = Bundle().apply { putInt("id", id) }
                }
                replace(R.id.rootFragment, detailsFragment)
                setReorderingAllowed(true)
                addToBackStack("to${id}EpisodeDetails")
            }
        }

        val characterId = arguments?.getInt("id", 0)!!
        viewModel.loadCharacterDetails(
            characterId,
            {
                Toast.makeText(
                    context,
                    "Oops! We can't find this character...",
                    Toast.LENGTH_SHORT
                ).show()
                activity?.supportFragmentManager?.popBackStack()
            }
        )

        binding.episodesListWrapper.setOnRefreshListener {
            if (!hasInternetAccess()) {
                Toast.makeText(
                    context,
                    "We cannot load episodes. Make sure the Internet connection is available",
                    Toast.LENGTH_SHORT
                ).show()
                binding.episodesListWrapper.isRefreshing = false
                return@setOnRefreshListener
            }

            viewModel.loadCharacterDetails(
                characterId,
                {
                    Toast.makeText(
                        context,
                        "Oops! We can't find this character...",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                {
                    binding.episodesListWrapper.isRefreshing = false
                },
                true
            )
        }

        return binding.root
    }

    override fun onResume() {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onResume()
    }

    override fun onPause() {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        super.onPause()
    }

    private fun hasInternetAccess(): Boolean =
        (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo?.isConnected ?: false
}