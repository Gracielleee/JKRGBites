package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.jrkg.jrkgbites.databinding.FragmentRestaurantDetailsBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.utils.ImageStorageUtils
import com.jrkg.jrkgbites.utils.ToastUtils
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RestaurantDetailsFragment : Fragment() {

    private var _binding: FragmentRestaurantDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private val args: RestaurantDetailsFragmentArgs by navArgs()

    private var currentRestaurantId: String? = null

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

        binding.toggleFavoriteButton.setOnClickListener {
            currentRestaurantId?.let { id ->
                viewLifecycleOwner.lifecycleScope.launch {
                    val favorites = viewModel.favoritesList.value
                    val isFavorited = favorites.any { it.id == id }
                    viewModel.toggleFavorite(id)
                    
                    val message = if (isFavorited) "Removed from Favorites" else "Added to Favorites"
                    val type = if (isFavorited) ToastUtils.ToastType.INFO else ToastUtils.ToastType.SUCCESS
                    
                    ToastUtils.showCustomToast(
                        context = requireContext(),
                        message = message,
                        type = type,
                        durationMs = 1000L,
                        gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL,
                        yOffset = 200
                    )
                }
            }
        }

        binding.toggleNeverAgainButton.setOnClickListener {
            currentRestaurantId?.let { id ->
                viewLifecycleOwner.lifecycleScope.launch {
                    val neverAgain = viewModel.neverAgainList.value
                    val isNeverAgain = neverAgain.any { it.id == id }
                    viewModel.toggleNeverAgain(id)

                    val message = if (isNeverAgain) "Removed from Never Again. Restaurant Restored." else "Added to Never Again. You can find this restaurant again in the Search page."
                    val type = if (isNeverAgain) ToastUtils.ToastType.INFO else ToastUtils.ToastType.SUCCESS

                    ToastUtils.showCustomToast(
                        context = requireContext(),
                        message = message,
                        type = type,
                        durationMs = 1000L,
                        gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL,
                        yOffset = 200
                    )
                }
            }
        }

        // Observe favorites to update the UI
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoritesList.collect { favorites ->
                val isFavorited = favorites.any { it.id == currentRestaurantId }
                updateFavoriteButton(isFavorited)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.neverAgainList.collect { neverAgain ->
                val isNeverAgain = neverAgain.any { it.id == currentRestaurantId }
                updateNeverAgainButton(isNeverAgain)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getRestaurantById(currentRestaurantId!!).collect { restaurant ->
                restaurant?.let {
                    displayRestaurantDetails(it)
                    setupRatingSection(it)
                    observeExistingRating(it.id)
                } ?: run {
                    ToastUtils.showCustomToast(
                        context = requireContext(),
                        message = "Restaurant not found!",
                        type = ToastUtils.ToastType.ERROR,
                        durationMs = 1500L,
                        gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL,
                        yOffset = 200
                    )
                    findNavController().navigateUp()
                }
            }
        }
    }

    private fun updateFavoriteButton(isFavorited: Boolean) {
        val icon = if (isFavorited) {
            R.drawable.favorite_ic_filled  // Solid heart
        } else {
            R.drawable.favorite_border_ic  // Empty heart outline
        }
        binding.toggleFavoriteButton.setImageResource(icon)
    }

    private fun updateNeverAgainButton(isNeverAgain: Boolean) {
        val icon = if (isNeverAgain) {
            R.drawable.ic_trash_restore  // Restore from Trash
        } else {
            R.drawable.ic_trash  // Can Trash
        }
        binding.toggleNeverAgainButton.setImageResource(icon)
    }

    private fun displayRestaurantDetails(restaurant: Restaurant) {
        binding.restaurantName.text = restaurant.name.orEmpty()
        binding.restaurantCategoryCuisine.text = "${restaurant.cuisine.orEmpty()} • ${restaurant.category.orEmpty()}"
        binding.restaurantLevel.text = "Level: ${restaurant.level.orEmpty()}"
        binding.restaurantTags.text = "Tags: ${restaurant.tags?.joinToString(", ").orEmpty()}"

//        // Load image using the explicit logoResourceName
//        val resId = if (!restaurant.logoResourceName.isNullOrEmpty()) {
//            context?.resources?.getIdentifier(restaurant.logoResourceName, "drawable", context?.packageName) ?: 0
//        } else {
//            0
//        }
//
//        if (resId != 0) {
//            binding.restaurantImage.setImageResource(resId)
//        } else {
//            binding.restaurantImage.setImageResource(android.R.drawable.ic_menu_gallery)
//        }
        val logoData = ImageStorageUtils.getLogo(requireContext(), restaurant.id, restaurant.name)
        binding.restaurantImage.load(logoData ?: android.R.drawable.ic_menu_gallery) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery)
            error(android.R.drawable.ic_menu_gallery)
        }
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
                ToastUtils.showCustomToast(
                    context = requireContext(),
                    message = getString(R.string.toast_select_star_rating),
                    type = ToastUtils.ToastType.WARNING,
                    durationMs = 1500L,
                    gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL,
                    yOffset = 200
                )
                return@setOnClickListener
            }

            val ratingValue = rating.toInt()
            viewModel.submitRating(restaurant.id, ratingValue, comment)

            ToastUtils.showCustomToast(
                context = requireContext(),
                message = getString(R.string.toast_rating_submitted_for, restaurant.name.orEmpty()),
                type = ToastUtils.ToastType.SUCCESS,
                durationMs = 1500L,
                gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL,
                yOffset = 200
            )

            if (ratingValue < com.jrkg.jrkgbites.domain.RatingManager.RATING_THRESHOLD) {
                showNeverAgainDialog(restaurant.id)
            }
            commentInput.setText("")
        }

        cancelButton.setOnClickListener {
            ratingBar.rating = 0f
            commentInput.setText("")
            findNavController().navigateUp()
        }
    }

    private fun observeExistingRating(restaurantId: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allRestaurantRatings.collect { ratings ->
                val existingRating = ratings.find { it.restaurantId == restaurantId }
                existingRating?.let {
                    binding.restaurantRatingBar.rating = it.rating.toFloat()
                    binding.ratingCommentInput.setText(it.comment)
                } ?: run {
                    binding.restaurantRatingBar.rating = 0f
                    binding.ratingCommentInput.setText("")
                }
            }
        }
    }

    private fun showNeverAgainDialog(restaurantId: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_never_again_title))
            .setMessage(getString(R.string.dialog_never_again_message))
            .setPositiveButton(getString(R.string.dialog_never_again_positive)) { _, _ ->
                viewModel.addToNeverAgainFromRating(restaurantId)
            }
            .setNegativeButton(getString(R.string.dialog_never_again_negative), null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
