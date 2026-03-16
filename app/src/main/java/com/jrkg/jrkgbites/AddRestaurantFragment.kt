package com.jrkg.jrkgbites

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.jrkg.jrkgbites.databinding.FragmentAddRestaurantBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.utils.ImageStorageUtils
import com.jrkg.jrkgbites.utils.ToastUtils
import com.jrkg.jrkgbites.utils.ValidationUtils
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import com.jrkg.jrkgbites.viewmodel.MainViewModelFactory
import io.viascom.nanoid.NanoId
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController

class AddRestaurantFragment : Fragment(R.layout.fragment_add_restaurant) {

    private lateinit var binding: FragmentAddRestaurantBinding
    private var selectedImageUri: Uri? = null

    private lateinit var viewModel: MainViewModel

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            binding.ivRestaurantLogo.setImageURI(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = MainViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        binding = FragmentAddRestaurantBinding.bind(view)

        binding.btnPickImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            saveRestaurant()
        }
    }

    private fun saveRestaurant() {
        val nameInput = binding.etName.text.toString().trim()
        val categoryInput = binding.etCategory.text.toString().trim()
        val cuisineInput = binding.etCuisine.text.toString().trim()
        val levelInput = binding.etLevel.text.toString().trim()
        val locationInput = binding.etLocation.text.toString().trim()
        val tagsInput = binding.etTags.text.toString().trim()
        val latInput = binding.etLat.text.toString().trim()
        val lngInput = binding.etLong.text.toString().trim()

        // Check if the image is selected
        if (selectedImageUri == null || selectedImageUri.toString().isEmpty()) {
            ToastUtils.showCustomToast(
                requireContext(),
                "Please select an image",
                ToastUtils.ToastType.ERROR
            )
            return
        }

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

        val restaurantId = NanoId.generate(8)
        var logoResourceName: String? = null
        val tags: List<String>? = tagsInput.split(",")

        // Save image locally
        selectedImageUri?.let { uri ->
            logoResourceName = ImageStorageUtils.saveImageToInternalStorage(requireContext(), uri, restaurantId)
        }

        val newRestaurant = Restaurant(
            id = restaurantId,
            addedBy = "", // Set in RestaurantRepository
            isPublic = false,
            name = nameInput,
            category = categoryInput,
            location = locationInput,
            cuisine = cuisineInput,
            level = levelInput,
            lat = latInput,
            lng = lngInput,
            logoResourceName = logoResourceName,
            tags = tags ?: emptyList()
        )

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                viewModel.createRestaurant(newRestaurant)

                context?.let {
                    Toast.makeText(it, "Restaurant Saved Successfully!", Toast.LENGTH_SHORT).show()
                }

                findNavController().popBackStack()
            } catch (e: Exception) {

                context?.let {
                    Toast.makeText(it, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

}