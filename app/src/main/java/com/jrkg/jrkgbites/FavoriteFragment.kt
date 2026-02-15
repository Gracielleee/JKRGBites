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
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    private var currentSelectedCategory: String = "All"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        // 1. Set Managers to match your old UI
        binding.recentlyAddedRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerId.layoutManager = GridLayoutManager(requireContext(), 2)

        // 2. Setup ChipGroup
        setupCategoryChips()

        // 3. Observe the data flow
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoritesList.collect { restaurantList ->
                if (restaurantList.isNotEmpty()) {
                    val recentlyAddedList = restaurantList.take(6)
                    binding.recentlyAddedRecycler.adapter = com.jrkg.jrkgbites.adapter.RestaurantAdapter(requireContext(), recentlyAddedList)

                    //Load all favorites by default
                    updateRestaurantLists(restaurantList)
                }
            }
        }

        // 4. Navigation
        binding.seeAllFavorites.setOnClickListener { findNavController().navigate(R.id.see_all_favorites) }
        binding.seeAllCategory.setOnClickListener { findNavController().navigate(R.id.see_all_category) }
    }

    //Dynamic categories creation
    private fun generateCategories(): List<String> {
        val categorySet = mutableSetOf<String>()

        viewModel.favoritesList.value.forEach { restaurant ->
            restaurant.category?.takeIf { it.isNotBlank() }?.let { categorySet.add(it) }
//            restaurant.cuisine?.takeIf { it.isNotBlank() }?.let { categorySet.add(it) }
        }

        return listOf("All") + categorySet.sorted()
    }


    private fun setupCategoryChips() {
        // Define categories
        val categories = generateCategories()

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
//                        restaurant.cuisine == category
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