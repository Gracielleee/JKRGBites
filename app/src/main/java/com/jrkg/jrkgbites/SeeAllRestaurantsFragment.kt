package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jrkg.jrkgbites.databinding.FragmentSeeAllRestaurantsBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.viewmodel.MainViewModel

class SeeAllRestaurantsFragment : Fragment() {

    private var _binding: FragmentSeeAllRestaurantsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeeAllRestaurantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Sharing the ViewModel with MainActivity to keep favorite status synced
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        // 2. Setup the Grid with 2 columns
        // This matches your XML ID: android:id="@+id/all_restaurants_recycler"
        binding.allRestaurantsRecycler.layoutManager = GridLayoutManager(requireContext(), 2)

        // 3. Get arguments (if you passed any from FavoriteFragment)
        val showOnlyFavorites = arguments?.getBoolean("SHOW_ONLY_FAVORITES") ?: false

        // 4. Use data from ViewModel
        val fullList = viewModel.deck.value ?: emptyList()
        val displayList = if (showOnlyFavorites) {
            fullList.filter { it.isFavorite }
        } else {
            fullList
        }

        // 5. Set the Adapter
        binding.allRestaurantsRecycler.adapter = RestaurantAdapter(requireContext(), displayList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}