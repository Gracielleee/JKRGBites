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
import com.jrkg.jrkgbites.databinding.FragmentFavoriteBinding
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

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

        // 2. Observe the data flow
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.deck.collect { restaurantList ->
                if (restaurantList.isNotEmpty()) {
                    // Top Section (Old logic: take first 6)
                    val recentlyAddedList = restaurantList.take(6)
                    binding.recentlyAddedRecycler.adapter = RestaurantAdapter(requireContext(), recentlyAddedList)

                    // Bottom Section (Old logic: next 12)
                    val categoryList = restaurantList.drop(6).take(12)
                    binding.categoryRecyclerId.adapter = RestaurantAdapter(requireContext(), categoryList)
                }
            }
        }

        // 3. Navigation
        binding.seeAllFavorites.setOnClickListener { findNavController().navigate(R.id.see_all_favorites) }
        binding.seeAllCategory.setOnClickListener { findNavController().navigate(R.id.see_all_category) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}