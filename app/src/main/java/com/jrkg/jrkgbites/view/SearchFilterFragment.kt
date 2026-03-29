package com.jrkg.jrkgbites.view

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.jrkg.jrkgbites.R
import com.jrkg.jrkgbites.databinding.FragmentSearchFilterBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.services.FilterService
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class SearchFilterFragment : Fragment() {

    private var _binding: FragmentSearchFilterBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    private var initialCategories = setOf("All")
    private var initialCuisines = setOf("All")
    private var initialLevels = setOf("All")
    private var initialTags = setOf("All")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        // Load initial values from arguments if they exist
        arguments?.let {
            initialCategories = it.getString("category", "All").split(",").toSet()
            initialCuisines = it.getString("cuisine", "All").split(",").toSet()
            initialLevels = it.getString("level", "All").split(",").toSet()
            initialTags = it.getString("tag", "All").split(",").toSet()
        }

        setupExpandableSections()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allRestaurants.collect { restaurants ->
                if (restaurants.isNotEmpty()) {
                    populateAllChipGroups(restaurants)
                }
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.applyFiltersButton.setOnClickListener {
            val result = Bundle().apply {
                putString("category", getCheckedChipTexts(binding.categoryChipGroup))
                putString("cuisine", getCheckedChipTexts(binding.cuisineChipGroup))
                putString("level", getCheckedChipTexts(binding.levelChipGroup))
                putString("tag", getCheckedChipTexts(binding.tagsChipGroup))
            }
            parentFragmentManager.setFragmentResult("filter_request", result)
            findNavController().navigateUp()
        }

        binding.clearFiltersButton.setOnClickListener {
            clearAllSelections()
            val result = Bundle().apply {
                putString("category", "All")
                putString("cuisine", "All")
                putString("level", "All")
                putString("tag", "All")
            }
            parentFragmentManager.setFragmentResult("filter_request", result)
            findNavController().navigateUp()
        }
    }

    private fun clearAllSelections() {
        resetChipGroup(binding.categoryChipGroup)
        resetChipGroup(binding.cuisineChipGroup)
        resetChipGroup(binding.levelChipGroup)
        resetChipGroup(binding.tagsChipGroup)
    }

    private fun resetChipGroup(group: ChipGroup) {
        for (i in 0 until group.childCount) {
            val chip = group.getChildAt(i) as? Chip
            chip?.isChecked = (chip?.text == "All")
        }
    }

    private fun setupExpandableSections() {
        setupToggle(binding.headerCategory, binding.categoryChipGroup, binding.arrowCategory)
        setupToggle(binding.headerCuisine, binding.cuisineChipGroup, binding.arrowCuisine)
        setupToggle(binding.headerLevel, binding.levelChipGroup, binding.arrowLevel)
        setupToggle(binding.headerTags, binding.tagsChipGroup, binding.arrowTags)
    }

    private fun setupToggle(header: View, content: View, arrow: View) {
        // Start collapsed for a cleaner look, or VISIBLE if you want it open by default
        content.visibility = View.GONE
        arrow.rotation = 0f

        header.setOnClickListener {
            val isExpanding = content.visibility == View.GONE
            
            // Apply a smooth transition for the height change
            TransitionManager.beginDelayedTransition(binding.root as ViewGroup)
            
            content.visibility = if (isExpanding) View.VISIBLE else View.GONE
            
            // Animate the arrow rotation
            arrow.animate()
                .rotation(if (isExpanding) 180f else 0f)
                .setDuration(300)
                .start()
        }
    }

    private fun populateAllChipGroups(restaurants: List<Restaurant>) {
        populateChipGroup(binding.categoryChipGroup, FilterService.generateCategoryFilter(restaurants), initialCategories)
        populateChipGroup(binding.cuisineChipGroup, FilterService.generateCuisineFilter(restaurants), initialCuisines)
        populateChipGroup(binding.levelChipGroup, FilterService.generateLevelFilter(restaurants), initialLevels)
        populateChipGroup(binding.tagsChipGroup, FilterService.generateTagsFilter(restaurants), initialTags)
    }

    private fun populateChipGroup(group: ChipGroup, strings: List<String>, selectedSet: Set<String>) {
        group.removeAllViews()
        val styledContext = ContextThemeWrapper(requireContext(), R.style.CustomChipStyle)
        
        strings.forEach { text ->
            val chip = Chip(styledContext).apply {
                this.text = text
                id = View.generateViewId()
                isCheckable = true
                isChecked = selectedSet.contains(text)

                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        if (text == "All") {
                            uncheckOthers(group, this)
                        } else {
                            uncheckAllChip(group)
                        }
                    }
                }
            }
            group.addView(chip)
        }
    }

    private fun uncheckOthers(group: ChipGroup, currentChip: Chip) {
        for (i in 0 until group.childCount) {
            val chip = group.getChildAt(i) as? Chip
            if (chip != null && chip != currentChip) {
                chip.isChecked = false
            }
        }
    }

    private fun uncheckAllChip(group: ChipGroup) {
        for (i in 0 until group.childCount) {
            val chip = group.getChildAt(i) as? Chip
            if (chip != null && chip.text == "All") {
                chip.isChecked = false
            }
        }
    }

    private fun getCheckedChipTexts(group: ChipGroup): String {
        val selected = mutableListOf<String>()
        for (i in 0 until group.childCount) {
            val chip = group.getChildAt(i) as? Chip
            if (chip?.isChecked == true) {
                selected.add(chip.text.toString())
            }
        }
        return if (selected.isEmpty()) "All" else selected.joinToString(",")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
