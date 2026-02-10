package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import com.jrkg.jrkgbites.databinding.FragmentRestaurantDetailsBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RestaurantDetailsFragment : Fragment() {

    private var _binding: FragmentRestaurantDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private val args: RestaurantDetailsFragmentArgs by navArgs()

    private var currentRestaurantId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        currentRestaurantId = args.restaurantId

        viewLifecycleOwner.lifecycleScope.launch {
            // Fetch the specific restaurant from the deck (or any other source in ViewModel)
            val restaurant = viewModel.deck.filterNotNull().first().find { it.id.toInt() == currentRestaurantId }

            restaurant?.let {
                displayRestaurantDetails(it)
                setupRatingSection(it)
                observeExistingRating(it.id.toInt())
            } ?: run {
                // Handle case where restaurant is not found
                Toast.makeText(requireContext(), "Restaurant not found!", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }
    }

    private fun displayRestaurantDetails(restaurant: Restaurant) {
        binding.restaurantName.text = restaurant.name.orEmpty()
        binding.restaurantCategoryCuisine.text = "${restaurant.cuisine.orEmpty()} • ${restaurant.category.orEmpty()}"
        binding.restaurantLevel.text = "Level: ${restaurant.level.orEmpty()}"
        binding.restaurantTags.text = "Tags: ${restaurant.tags?.joinToString(", ").orEmpty()}"

        // Load image (similar logic to adapters)
        val resourceName = restaurant.name?.lowercase()
            ?.replace(" ", "")
            ?.replace("-", "")
            ?.replace(".", "")
            ?.replace("&", "")
            ?.replace("'", "")
            ?.replace("\u2019", "")
            ?.replace(",", "")
            ?.replace("!", "")
            ?.replace("?", "")
            ?.replace("/", "")
            ?: ""

        val resId = if (resourceName.isNotEmpty()) {
            context?.resources?.getIdentifier(resourceName, "drawable", context?.packageName) ?: 0
        } else {
            0
        }

        if (resId != 0) {
            binding.restaurantImage.setImageResource(resId)
        } else {
            binding.restaurantImage.setImageResource(android.R.drawable.ic_menu_gallery)
        }

        // TODO: Implement actual map integration using the restaurant's location data
        // For now, the FrameLayout serves as a placeholder.
    }

    private fun setupRatingSection(restaurant: Restaurant) {
        val ratingBar: RatingBar = binding.restaurantRatingBar
        val commentInput: TextInputEditText = binding.ratingCommentInput
        val submitButton = binding.submitRatingButton
        val cancelButton = binding.cancelRatingButton

        submitButton.setOnClickListener {
            val rating = ratingBar.rating
            val comment = commentInput.text.toString()

            if (rating == 0f) {
                Toast.makeText(requireContext(), "Please select a star rating.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.submitRating(restaurant.id.toInt(), rating.toInt(), comment)
            Toast.makeText(requireContext(), "Rating submitted for ${restaurant.name}!", Toast.LENGTH_SHORT).show()
            // After submission, optionally clear inputs or navigate back
            // For now, we will just update the displayed rating via the observer
            commentInput.setText("")
        }

        cancelButton.setOnClickListener {
            // Clear current input without submitting
            ratingBar.rating = 0f
            commentInput.setText("")
        }
    }

    private fun observeExistingRating(restaurantId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allRestaurantRatings.collect { ratings ->
                val existingRating = ratings.find { it.restaurantId.toInt() == restaurantId }
                existingRating?.let {
                    binding.restaurantRatingBar.rating = it.rating.toFloat()
                    binding.ratingCommentInput.setText(it.comment)
                } ?: run {
                    // No existing rating, reset UI
                    binding.restaurantRatingBar.rating = 0f
                    binding.ratingCommentInput.setText("")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}