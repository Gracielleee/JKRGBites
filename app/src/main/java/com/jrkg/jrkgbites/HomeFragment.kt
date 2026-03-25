package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView

import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jrkg.jrkgbites.adapter.RestaurantAdapter
import com.jrkg.jrkgbites.data.RestaurantRepository
import com.jrkg.jrkgbites.data.UserPreferencesManager
import com.jrkg.jrkgbites.data.source.FakeAuthService
import com.jrkg.jrkgbites.databinding.FragmentHomeBinding
import com.jrkg.jrkgbites.domain.*
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.services.BiometricService
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import com.jrkg.jrkgbites.viewmodel.MainViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    private lateinit var recyclerViewRestaurants: RecyclerView
    private lateinit var progressBarHome: ProgressBar
    private lateinit var textViewEmptyState: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewRestaurants = binding.recyclerViewRestaurants
                progressBarHome = binding.progressBarHome
                textViewEmptyState = binding.textViewEmptyState
        
                viewModel = ViewModelProvider(requireActivity(), MainViewModelFactory(requireActivity().application))[MainViewModel::class.java]
        
                setupRecyclerView()
                observeData()
    }

    private fun setupRecyclerView() {
        recyclerViewRestaurants.layoutManager = GridLayoutManager(context, 2)
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                combine(
                    viewModel.allRestaurants,
                    viewModel.neverAgainList
                ) { allRestaurants, neverAgainList ->
                    var filtered = allRestaurants.filter { restaurant ->
                        !neverAgainList.any { it.id == restaurant.id }
                    }

                    if (viewModel.isProximityEnabled()) {
                        val (userLat, userLng) = viewModel.getUserLocation()
                        if (userLat != 0.0 && userLng != 0.0) {
                            filtered = filtered.filter { restaurant ->
                                val resLat = restaurant.lat?.toDoubleOrNull() ?: 0.0
                                val resLng = restaurant.lng?.toDoubleOrNull() ?: 0.0
                                if (resLat != 0.0 && resLng != 0.0) {
                                    val results = FloatArray(1)
                                    android.location.Location.distanceBetween(userLat, userLng, resLat, resLng, results)
                                    results[0] <= 5000 // 5km limit
                                } else {
                                    true
                                }
                            }
                        }
                    }
                    filtered
                }.collectLatest { filteredRestaurants ->
                    updateRestaurantList(filteredRestaurants)
                }
            }
        }
    }


    private fun updateRestaurantList(restaurants: List<Restaurant>) {
        if (restaurants.isNotEmpty()) {
            recyclerViewRestaurants.adapter = RestaurantAdapter(requireContext(), restaurants, null)
            recyclerViewRestaurants.visibility = View.VISIBLE
            textViewEmptyState.visibility = View.GONE
        } else {
            recyclerViewRestaurants.visibility = View.GONE
            textViewEmptyState.visibility = View.VISIBLE
        }
        progressBarHome.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}