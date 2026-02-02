package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jrkg.jrkgbites.databinding.FragmentSeeRecentlyAddedBinding
import com.jrkg.jrkgbites.viewmodel.MainViewModel

class RecentlyAddedFavoriteFragment : Fragment() {

    private var _binding: FragmentSeeRecentlyAddedBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // This matches your XML filename: fragment_see_recently_added.xml
        _binding = FragmentSeeRecentlyAddedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Initialize ViewModel using requireActivity() to share data with MainActivity
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        // 2. Setup the 2-column grid layout
        // Ensure android:id="@+id/recentRecyclerView" is in your fragment_see_recently_added.xml
        binding.recentRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // 3. Filter data: Only show favorites
        val allRestaurants = viewModel.deck.value ?: emptyList()
        val favoriteRestaurants = allRestaurants.filter { it.isFavorite }

        // 4. Set the Adapter
        binding.recentRecyclerView.adapter = RestaurantAdapter(requireContext(), favoriteRestaurants)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}