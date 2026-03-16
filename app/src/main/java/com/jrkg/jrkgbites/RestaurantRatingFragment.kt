package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jrkg.jrkgbites.adapter.RestaurantRatingAdapter
import com.jrkg.jrkgbites.databinding.FragmentRestaurantRatingBinding
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class RestaurantRatingFragment : Fragment() {

    private var _binding: FragmentRestaurantRatingBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var ratingAdapter: RestaurantRatingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        setupRecyclerView()
        observeRatings()

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupRecyclerView() {
        ratingAdapter = RestaurantRatingAdapter(
            onItemClick = { restaurantId ->
                // Navigate to details page when a rating is clicked
                val bundle = Bundle().apply {
                    putString("restaurantId", restaurantId)
                }
                findNavController().navigate(R.id.to_restaurantDetailsFragment, bundle)
            },
            onRatingUpdate = {
                viewModel.submitRating(it.restaurantId, it.rating.toInt(), it.comment)
            },
            onRatingDelete = { rating ->
                // Basic cleanup for now
                viewModel.submitRating(rating.restaurantId, 0, "")
            }
        )

        binding.ratingsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ratingAdapter
        }
    }

    private fun observeRatings() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allRestaurantRatings.collect { ratings ->
                if (ratings.isEmpty()) {
                    binding.ratingsRecyclerView.visibility = View.GONE
                    binding.noRatingsMessage.visibility = View.VISIBLE
                } else {
                    binding.ratingsRecyclerView.visibility = View.VISIBLE
                    binding.noRatingsMessage.visibility = View.GONE
                    ratingAdapter.submitList(ratings)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
