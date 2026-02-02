package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.SearchView
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jrkg.jrkgbites.adapter.RestaurantCardAdapter
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
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    private lateinit var recyclerViewRestaurants: RecyclerView
    private lateinit var progressBarHome: ProgressBar
    private lateinit var textViewEmptyState: TextView
    private lateinit var searchViewRestaurants: SearchView

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
        searchViewRestaurants = binding.searchViewRestaurants

        // Dependency Setup (replicated from MainActivity.kt and LoginFragment.kt)
        val context = requireContext().applicationContext
        val repository = RestaurantRepository(context)
        val prefsManager = UserPreferencesManager(context)
        val biometricService = BiometricService(context)
        val authService = FakeAuthService()

        // Domain Layer
        val picker = RestaurantPicker(repository)
        val swipeManager = SwipeManager(repository)
        val searchManager = SearchManager(repository)
        val ratingManager = RatingManager(swipeManager)
        val authManager = AuthManager(biometricService, prefsManager)
        val sessionManager = SessionManager(authService)

        // ViewModel
        val factory = MainViewModelFactory(
            picker,
            swipeManager,
            searchManager,
            ratingManager,
            authManager,
            prefsManager,
            sessionManager
        )
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        setupRecyclerView()
        setupSearchView()
        observeData() // Renamed to observe general data, including search results
    }

    private fun setupRecyclerView() {
        recyclerViewRestaurants.layoutManager = LinearLayoutManager(context)
    }

    private fun setupSearchView() {
        searchViewRestaurants.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query.orEmpty())
                searchViewRestaurants.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search(newText.orEmpty())
                return true
            }
        })
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Observe search results
                viewModel.searchResults.collectLatest { searchResults ->
                    updateRestaurantList(searchResults)
                }
                // Also, ensure initial deck is loaded if no search is active
                viewModel.deck.collectLatest { deck ->
                    // Only update if search results are empty (no active search)
                    if (viewModel.searchResults.value.isEmpty() && searchViewRestaurants.query.isNullOrEmpty()) {
                        updateRestaurantList(deck)
                    }
                }
            }
        }
    }

    private fun updateRestaurantList(restaurants: List<Restaurant>) {
        if (restaurants.isNotEmpty()) {
            recyclerViewRestaurants.adapter = RestaurantCardAdapter(restaurants)
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