package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jrkg.jrkgbites.adapter.RestaurantAdapter
import com.jrkg.jrkgbites.databinding.FragmentSeeAllFavRestaurantsBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.viewmodel.MainViewModel

class SeeAllRestaurantsFragment : Fragment() {

    private var _binding: FragmentSeeAllFavRestaurantsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeeAllFavRestaurantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Sharing the ViewModel with MainActivity to keep favorite status synced
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        // 2. Setup the Grid with 2 columns
        binding.allRestaurantsRecycler.layoutManager = GridLayoutManager(requireContext(), 2)

        // 4. Use data from ViewModel
        val displayList = viewModel.favoritesList.value ?: emptyList()

        // 5. Set the Adapter
        binding.allRestaurantsRecycler.adapter = RestaurantAdapter(requireContext(), displayList)

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}