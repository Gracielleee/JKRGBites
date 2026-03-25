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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.jrkg.jrkgbites.databinding.FragmentRestaurantDetailsBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.utils.ImageStorageUtils
import com.jrkg.jrkgbites.utils.ToastUtils
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import kotlinx.coroutines.delay
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

        // Observe restaurant details and handle silent pop on deletion
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getRestaurantById(currentRestaurantId!!).collect { restaurant ->
                if (restaurant != null) {
                    displayRestaurantDetails(restaurant)
                    setupRatingSection(restaurant)
                    observeExistingRating(restaurant.id)
                    updateIsPublicText(restaurant.isPublic == true)
                    willHideUnathorizedButtons(restaurant)
                } else {
                    // Small delay ensures MainActivity Toast shows up reliably
                    delay(100)
                    if (isAdded && findNavController().currentDestination?.id == R.id.restaurantDetailsFragment) {
                        findNavController().popBackStack()
                    }
                }
            }
        }

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
        
        binding.editRestaurantButton.setOnClickListener {
            currentRestaurantId?.let { id ->
                val bundle = Bundle().apply {
                    putString("restaurantId", id)
                }
                findNavController().navigate(R.id.action_restaurantDetailsFragment_to_updateRestaurantFragment, bundle)
            }
        }

        binding.deleteRestaurantButton.setOnClickListener {
            showDeleteConfirmationDialog()
        }

        binding.btnManageTags.setOnClickListener {
            if (viewModel.isPremiumActive()) {
                showTagPicker()
            } else {
                showPremiumUnlockDialog()
            }
        }

        // Observe favorites/never again to update the UI
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
            R.drawable.ic_unarchive  // Restore from Archive
        } else {
            R.drawable.ic_archive  // Can Archive
        }
        binding.toggleNeverAgainButton.setImageResource(icon)
    }

    private fun updateIsPublicText(isPublic: Boolean) {
        var stringDisplay = if (isPublic) {
            "Public"
        } else {
            "Private"
        }
        binding.isPublicBadge.text = stringDisplay
    }

    private fun willHideUnathorizedButtons(restaurant: Restaurant) {
        val isOwner = viewModel.isUserOwner(restaurant)
        if (isOwner) {
            binding.editRestaurantButton.visibility = View.VISIBLE
            binding.deleteRestaurantButton.visibility = View.VISIBLE
        } else {
            binding.editRestaurantButton.visibility = View.GONE
            binding.deleteRestaurantButton.visibility = View.GONE
        }
    }

    private fun displayRestaurantDetails(restaurant: Restaurant) {
        binding.restaurantName.text = restaurant.name.orEmpty()
        binding.restaurantCategoryCuisine.text = "${restaurant.cuisine.orEmpty()} • ${restaurant.category.orEmpty()}"
        binding.restaurantLevel.text = "Level: ${restaurant.level.orEmpty()}"
        binding.restaurantTags.text = "Tags: ${restaurant.tags?.joinToString(", ").orEmpty()}"

        val logoData = ImageStorageUtils.getLogo(requireContext(), restaurant.id, restaurant.name)
        binding.restaurantImage.load(logoData ?: android.R.drawable.ic_menu_gallery) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery)
            error(android.R.drawable.ic_menu_gallery)
        }

        // Setup Mini-Map
        binding.btnShowMap.setOnClickListener {
            val lat = restaurant.lat?.toDoubleOrNull()
            val lng = restaurant.lng?.toDoubleOrNull()
            if (lat != null && lng != null) {
                loadMap(lat, lng, restaurant.name ?: "Restaurant")
                binding.btnShowMap.visibility = View.GONE
            } else {
                ToastUtils.showCustomToast(
                    requireContext(),
                    "No location coordinates available for this restaurant.",
                    ToastUtils.ToastType.INFO
                )
            }
        }
    }

    private fun loadMap(lat: Double, lng: Double, name: String) {
        binding.mapProgressBar.visibility = View.VISIBLE
        binding.tvMapPlaceholder.visibility = View.GONE

        val mapFragment = SupportMapFragment.newInstance()
        childFragmentManager.beginTransaction()
            .replace(R.id.map_container, mapFragment)
            .commit()

        mapFragment.getMapAsync { googleMap ->
            binding.mapProgressBar.visibility = View.GONE
            val location = LatLng(lat, lng)
            googleMap.addMarker(MarkerOptions().position(location).title(name))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16f))
            googleMap.uiSettings.isZoomControlsEnabled = true
            googleMap.uiSettings.isMapToolbarEnabled = true // Shows the "Open in Google Maps" button
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

    private fun showDeleteConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete Restaurant")
            .setMessage("Are you sure you want to delete this restaurant? This action cannot be undone.")
            .setPositiveButton("Delete") { _, _ ->
                viewLifecycleOwner.lifecycleScope.launch {
                    val restaurant = viewModel.getRestaurantById(currentRestaurantId!!).first()
                    restaurant?.let { viewModel.deleteRestaurant(it) }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showNeverAgainDialog(restaurantId: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_never_again_title))
            .setMessage(getString(R.string.dialog_never_again_message))
            .setPositiveButton(getString(R.string.dialog_never_again_positive)) { _, _ ->
                viewModel.addToNeverAgainFromRating(restaurantId)
                ToastUtils.showCustomToast(
                    context = requireContext(),
                    message = "Added to Never Again. You can restore it in the Search page",
                    type = ToastUtils.ToastType.SUCCESS,
                    durationMs = 1500L,
                    gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL,
                    yOffset = 200
                )
            }
            .setNegativeButton(getString(R.string.dialog_never_again_negative), null)
            .show()
    }

    private fun showPremiumUnlockDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Unlock Custom Personalization 💎")
            .setMessage("Custom tags like 'Date Night' or 'Family Gathering' are a premium feature. Would you like to start your 15-day free trial?")
            .setPositiveButton("Start Free Trial") { _, _ ->
                viewModel.startFreeTrial()
                ToastUtils.showCustomToast(
                    context = requireContext(),
                    message = "Trial Started! Enjoy custom tags.",
                    type = ToastUtils.ToastType.SUCCESS
                )
                showTagPicker()
            }
            .setNeutralButton("Subscribe Now") { _, _ ->
                viewModel.subscribeUser()
                ToastUtils.showCustomToast(
                    context = requireContext(),
                    message = "Welcome to JRKGBites Premium!",
                    type = ToastUtils.ToastType.SUCCESS
                )
                showTagPicker()
            }
            .setNegativeButton("Maybe later", null)
            .show()
    }

    private fun showTagPicker() {
        val availableTags = arrayOf("Date Night", "Family Gathering", "Future Plans", "Healthy Choice", "Quick Bite", "Hidden Gem")
        val checkedItems = BooleanArray(availableTags.size)
        
        viewLifecycleOwner.lifecycleScope.launch {
            val restaurant = viewModel.getRestaurantById(currentRestaurantId!!).first()
            val currentTags = restaurant?.tags ?: emptyList()
            
            for (i in availableTags.indices) {
                checkedItems[i] = currentTags.contains(availableTags[i])
            }

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Personalize this Restaurant")
                .setMultiChoiceItems(availableTags, checkedItems) { _, which, isChecked ->
                    checkedItems[which] = isChecked
                }
                .setPositiveButton("Save") { _, _ ->
                    val selectedTags = mutableListOf<String>()
                    for (i in availableTags.indices) {
                        if (checkedItems[i]) selectedTags.add(availableTags[i])
                    }
                    saveTags(selectedTags)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    private fun saveTags(tags: List<String>) {
        viewLifecycleOwner.lifecycleScope.launch {
            val restaurant = viewModel.getRestaurantById(currentRestaurantId!!).first()
            restaurant?.let {
                val updated = it.copy(tags = tags)
                viewModel.updateRestaurant(updated)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
