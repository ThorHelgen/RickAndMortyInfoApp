package com.thorhelgen.rickandmortyinfoapp.presentation.details.locations

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.thorhelgen.rickandmortyinfoapp.R
import com.thorhelgen.rickandmortyinfoapp.databinding.FragmentLocationDetailsBinding
import com.thorhelgen.rickandmortyinfoapp.presentation.App
import com.thorhelgen.rickandmortyinfoapp.presentation.MainActivity
import com.thorhelgen.rickandmortyinfoapp.presentation.ViewModelFactory
import com.thorhelgen.rickandmortyinfoapp.presentation.details.characters.CharacterDetailsFragment
import com.thorhelgen.rickandmortyinfoapp.presentation.grid.GridAdapter
import javax.inject.Inject

class LocationDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: LocationDetailsViewModel by viewModels({requireActivity()}) { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        App.appComponent.inject(this)

        activity?.title = "Location Details"

        val binding = FragmentLocationDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.residentsGrid.adapter = GridAdapter { id ->
            activity?.supportFragmentManager?.commit {
                val detailsFragment = CharacterDetailsFragment().apply {
                    arguments = Bundle().apply { putInt("id", id) }
                }
                replace(R.id.rootFragment, detailsFragment)
                setReorderingAllowed(true)
                addToBackStack("to${id}CharacterDetails")
            }
        }
        activity?.actionBar?.title = "Location Details"

        val locationId = arguments?.getInt("id", 0)!!
        viewModel.loadLocationDetails(
            locationId,
            {
                Toast.makeText(
                    context,
                    "Oops! We can't find this location...",
                    Toast.LENGTH_SHORT
                ).show()
                activity?.supportFragmentManager?.popBackStack()
            }
        )

        binding.residentsListWrapper.setOnRefreshListener {
            if (!hasInternetAccess()) {
                Toast.makeText(
                    context,
                    "We cannot load residents. Make sure the Internet connection is available",
                    Toast.LENGTH_SHORT
                ).show()
                binding.residentsListWrapper.isRefreshing = false
                return@setOnRefreshListener
            }

            viewModel.loadLocationDetails(
                locationId,
                {
                    Toast.makeText(
                        context,
                        "Oops! We can't find this location...",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                {
                    binding.residentsListWrapper.isRefreshing = false
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