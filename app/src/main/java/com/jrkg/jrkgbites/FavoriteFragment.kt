package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.jrkg.jrkgbites.databinding.FragmentFavoriteBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.services.FilterService
import com.jrkg.jrkgbites.services.ShakeDetector
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var shakeDetector: ShakeDetector

    private var currentSelectedCategory: String = "All"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        // 1. Set Managers
        binding.recentlyAddedRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerId.layoutManager = GridLayoutManager(requireContext(), 2)

        // 2. Setup ShakeDetector
        shakeDetector = ShakeDetector(requireContext()).apply {
            setThreshold(18f) // Intentional shake only
            setOnShakeListener {
                if (viewModel.favoritesList.value.isNotEmpty()) {
                    navigateToRoulette()
                }
            }
        }

        // 3. Setup FAB
        binding.fabSpinWheel.setOnClickListener {
            navigateToRoulette()
        }

        // 4. Setup ChipGroup
        setupCategoryChips()

        // 5. Observe the data flow
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoritesList.collect { restaurantList ->
                if (restaurantList.isNotEmpty()) {
                    binding.emptyFavoritesText.visibility = View.GONE
                    binding.favoritesContentGroup.visibility = View.VISIBLE
                    binding.fabSpinWheel.visibility = View.VISIBLE

                    val recentlyAddedList = restaurantList.take(6)
                    binding.recentlyAddedRecycler.adapter = com.jrkg.jrkgbites.adapter.RestaurantAdapter(requireContext(), recentlyAddedList)

                    //Load all favorites by default
                    updateRestaurantLists(restaurantList)
                    
                    // Update chips as they might depend on data
                    setupCategoryChips()
                } else {
                    binding.emptyFavoritesText.visibility = View.VISIBLE
                    binding.favoritesContentGroup.visibility = View.GONE
                    binding.fabSpinWheel.visibility = View.GONE
                }
            }
        }

        // 6. Navigation
        binding.seeAllFavorites.setOnClickListener { findNavController().navigate(R.id.see_all_favorites) }
        binding.seeAllCategory.setOnClickListener { findNavController().navigate(R.id.see_all_category) }
    }

    private fun navigateToRoulette() {
        if (findNavController().currentDestination?.id == R.id.nav_favorite) {
            val bundle = Bundle().apply {
                putBoolean("shouldSpin", true)
            }
            findNavController().navigate(R.id.action_favoriteFragment_to_rouletteFragment, bundle)
        }
    }

    override fun onResume() {
        super.onResume()
        shakeDetector.start()
    }

    override fun onPause() {
        super.onPause()
        shakeDetector.stop()
    }


    private fun setupCategoryChips() {
        // Define categories
        val categories = FilterService.generateCategoryFilter(viewModel.favoritesList.value)

        // Clear existing chips
        binding.chipGroupCategories.removeAllViews()
        binding.chipGroupCategories.isSelectionRequired = true
        binding.chipGroupCategories.isSingleSelection = true

        // Create and add chips
        categories.forEach { category ->
            val chip = Chip(requireContext(), null, R.style.CustomChipStyle).apply {
                id = View.generateViewId()
                text = category
                isCheckable = true
            }

            chip.setOnClickListener {
                currentSelectedCategory = category
                val filteredList = if (category == "All") {
                    viewModel.favoritesList.value
                } else {
                    viewModel.favoritesList.value.filter { restaurant ->
                        restaurant.category == category
                    }
                }
                updateRestaurantLists(filteredList)

            }

            binding.chipGroupCategories.addView(chip)
        }

        // Set first chip (All) as default selected
        val firstChip = binding.chipGroupCategories.getChildAt(0) as? Chip
        firstChip?.isChecked = true
    }

    private fun updateRestaurantLists(restaurantList: List<Restaurant>) {
        // Category Section update
        binding.categoryRecyclerId.adapter = com.jrkg.jrkgbites.adapter.RestaurantAdapter(requireContext(), restaurantList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}