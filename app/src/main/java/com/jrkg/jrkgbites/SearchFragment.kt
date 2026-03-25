package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jrkg.jrkgbites.databinding.FragmentSearchBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.adapter.RestaurantAdapter
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: RestaurantAdapter
    private var fullRestaurantList = listOf<Restaurant>()
    
    private var currentQuery: String = ""
    
    // Filter states
    private var currentCategory: String = "All"
    private var currentCuisine: String = "All"
    private var currentLevel: String = "All"
    private var currentTag: String = "All"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        binding.searchResultsRecycler.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter = RestaurantAdapter(requireContext(), emptyList(), null)
        binding.searchResultsRecycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allRestaurants.collect { restaurants ->
                if (restaurants.isNotEmpty()) {
                    fullRestaurantList = restaurants
                    applyFilters()
                }
            }
        }

        binding.addRestoBtn.setOnClickListener {
            findNavController().navigate(R.id.to_addRestaurant)
        }

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                currentQuery = newText.orEmpty()
                applyFilters()
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean = false
        })

        binding.filterIcon.setOnClickListener {
            val bundle = Bundle().apply {
                putString("category", currentCategory)
                putString("cuisine", currentCuisine)
                putString("level", currentLevel)
                putString("tag", currentTag)
            }
            findNavController().navigate(R.id.action_nav_search_to_searchFilterFragment, bundle)
        }

        parentFragmentManager.setFragmentResultListener("filter_request", viewLifecycleOwner) { _, bundle ->
            currentCategory = bundle.getString("category", "All")
            currentCuisine = bundle.getString("cuisine", "All")
            currentLevel = bundle.getString("level", "All")
            currentTag = bundle.getString("tag", "All")
            applyFilters()
        }
    }

    private fun applyFilters() {
        val query = currentQuery.lowercase()

        var filteredList = fullRestaurantList.filter { restaurant ->
            val matchesQuery = currentQuery.isEmpty() ||
                    listOf(restaurant.name, restaurant.category, restaurant.cuisine)
                        .any { it?.lowercase()?.contains(query) == true }

            val matchesFilters =
                (currentCategory == "All" || restaurant.category == currentCategory) &&
                        (currentCuisine == "All" || restaurant.cuisine == currentCuisine) &&
                        (currentLevel == "All" || restaurant.level == currentLevel) &&
                        (currentTag == "All" || restaurant.tags?.contains(currentTag) == true)

            matchesQuery && matchesFilters
        }

        // Apply proximity filter if enabled
        if (viewModel.isProximityEnabled()) {
            val (userLat, userLng) = viewModel.getUserLocation()
            if (userLat != 0.0 && userLng != 0.0) {
                filteredList = filteredList.filter { restaurant ->
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

        adapter.updateList(filteredList.sortedBy { it.name })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
