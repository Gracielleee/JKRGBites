package com.jrkg.jrkgbites.view

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.jrkg.jrkgbites.R
import com.jrkg.jrkgbites.databinding.FragmentUpdateRestaurantBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.utils.ImageStorageUtils
import com.jrkg.jrkgbites.utils.ToastUtils
import com.jrkg.jrkgbites.utils.ValidationUtils
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import com.jrkg.jrkgbites.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import kotlinx.coroutines.flow.first

class UpdateRestaurantFragment : Fragment(R.layout.fragment_update_restaurant) {

    private lateinit var binding: FragmentUpdateRestaurantBinding
    private var selectedImageUri: Uri? = null
    private var currentRestaurant: Restaurant? = null

    private lateinit var viewModel: MainViewModel
    private val args: UpdateRestaurantFragmentArgs by navArgs()

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            binding.ivRestaurantLogo.setImageURI(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = MainViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]

        binding = FragmentUpdateRestaurantBinding.bind(view)

        binding.btnSave.text = "Update Restaurant"

        // Load existing restaurant data to display
        loadRestaurantData()

        binding.btnPickImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            updateRestaurant()
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.toastMessage.collect { message ->
                message?.let {
                    ToastUtils.showCustomToast(
                        requireContext(),
                        it,
                        if (it.contains("successfully")) ToastUtils.ToastType.SUCCESS
                        else ToastUtils.ToastType.ERROR
                    )

                    viewModel.clearToastMessage()
                }
            }
        }
    }

    private fun loadRestaurantData() {
        viewLifecycleOwner.lifecycleScope.launch {
            val restaurantId = args.restaurantId
            val restaurant = viewModel.getRestaurantById(restaurantId).first()
            if (restaurant != null) {
                currentRestaurant = restaurant
                populateFields(restaurant)
            } else {
                ToastUtils.showCustomToast(
                    requireContext(),
                    "Restaurant not found",
                    ToastUtils.ToastType.ERROR
                )
                findNavController().popBackStack()
            }
        }
    }

    private fun populateFields(restaurant: Restaurant) {
        binding.etName.setText(restaurant.name)
        binding.etCategory.setText(restaurant.category)
        binding.etCuisine.setText(restaurant.cuisine)
        binding.etLevel.setText(restaurant.level)
        binding.etLocation.setText(restaurant.location)
        binding.etLat.setText(restaurant.lat ?: "0.0")
        binding.etLong.setText(restaurant.lng ?: "0.0")
        binding.etTags.setText(restaurant.tags?.joinToString(", "))

        val logoData = ImageStorageUtils.getLogo(requireContext(), restaurant.id, restaurant.name)
        binding.ivRestaurantLogo.load(logoData ?: android.R.drawable.ic_menu_gallery) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery)
            error(android.R.drawable.ic_menu_gallery)
        }
    }

    private fun updateRestaurant() {
        val nameInput = binding.etName.text.toString().trim()
        val categoryInput = binding.etCategory.text.toString().trim()
        val cuisineInput = binding.etCuisine.text.toString().trim()
        val levelInput = binding.etLevel.text.toString().trim()
        val locationInput = binding.etLocation.text.toString().trim()
        val latInput = binding.etLat.text.toString().trim()
        val longInput = binding.etLong.text.toString().trim()
        val tagsInput = binding.etTags.text.toString().trim()

        // Check for required fields
        if (nameInput.isEmpty() || categoryInput.isEmpty() || cuisineInput.isEmpty()) {
            ToastUtils.showCustomToast(
                requireContext(),
                "Please fill in all required fields",
                ToastUtils.ToastType.ERROR
            )
            ValidationUtils.highlightErrorFields(lifecycleScope, binding.tilName, binding.tilCategory, binding.tilCuisine)
            return
        }

        val restaurantId = currentRestaurant?.id ?: return
        var logoResourceName = currentRestaurant?.logoResourceName
        val tags: List<String> = if (tagsInput.isNotEmpty()) tagsInput.split(",").map { it.trim() } else emptyList()

        // Save image locally if a new one was selected
        selectedImageUri?.let { uri ->
            logoResourceName = ImageStorageUtils.saveImageToInternalStorage(requireContext(), uri, restaurantId)
        }

        val updatedRestaurant = currentRestaurant?.copy(
            name = nameInput,
            category = categoryInput,
            location = locationInput,
            cuisine = cuisineInput,
            level = levelInput,
            lat = if (latInput.isEmpty()) "0.0" else latInput,
            lng = if (longInput.isEmpty()) "0.0" else longInput,
            logoResourceName = logoResourceName,
            tags = tags
        ) ?: return

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                viewModel.updateRestaurant(updatedRestaurant)
                findNavController().popBackStack()
            } catch (e: Exception) {
                Log.e("UpdateRestaurantFragment", "Error updating restaurant: ${e.message}")
            }
        }
    }
}
