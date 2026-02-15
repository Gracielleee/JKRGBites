package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        binding.searchResultsRecycler.layoutManager = GridLayoutManager(requireContext(), 2)

        // Initialize adapter early to prevent crashes
        adapter = com.jrkg.jrkgbites.adapter.RestaurantAdapter(requireContext(), emptyList(), null)
        binding.searchResultsRecycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allRestaurants.collect { restaurants ->
                if (restaurants.isNotEmpty()) {
                    fullRestaurantList = restaurants
                    adapter.updateList(fullRestaurantList)
                }
            }
        }

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean = false
        })
    }

    private fun filter(text: String?) {
        val filteredList = if (text.isNullOrEmpty()) {
            fullRestaurantList
        } else {
            fullRestaurantList.filter { it.name?.lowercase()?.contains(text.lowercase()) == true }
        }
        adapter.updateList(filteredList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}