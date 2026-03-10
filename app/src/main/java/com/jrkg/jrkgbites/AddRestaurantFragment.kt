package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.jrkg.jrkgbites.data.api.RetrofitInstance
import com.jrkg.jrkgbites.data.api.RestaurantDto
import com.jrkg.jrkgbites.databinding.FragmentAddRestaurantBinding
import kotlinx.coroutines.launch
import java.util.UUID

class AddRestaurantFragment : Fragment(R.layout.fragment_add_restaurant) {

    private lateinit var binding: FragmentAddRestaurantBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddRestaurantBinding.bind(view)

        binding.btnSave.setOnClickListener {
            saveRestaurant()
        }
    }

    private fun saveRestaurant() {
        val nameInput = binding.etName.text.toString().trim()
        val categoryInput = binding.etCategory.text.toString().trim()
        val locationInput = binding.etLocation.text.toString().trim()

        if (nameInput.isEmpty()) {
            Toast.makeText(context, "Please enter a name", Toast.LENGTH_SHORT).show()
            return
        }

        // Fix: Matching all 12 required fields in RestaurantDto
        val newRestaurant = RestaurantDto(
            id = UUID.randomUUID().toString(), // Generate a unique ID
            addedBy = "currentUser",           // Placeholder for UserID
            isPublic = false,                    // TASK C: Keep it Private
            name = nameInput,
            category = categoryInput,
            location = locationInput,          // Use 'location' instead of 'address'
            cuisine = "",                      // Satisfying DTO requirement
            level = "",                        // Satisfying DTO requirement
            lat = "0.0",                       // Satisfying DTO requirement
            lng = "0.0",                       // Satisfying DTO requirement
            logoResourceName = "",             // Satisfying DTO requirement
            tags = emptyList()                 // Satisfying DTO requirement
        )

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // Fix: Using 'restaurantApi' from your RetrofitInstance
                RetrofitInstance.restaurantApi.createRestaurant(newRestaurant)

                Toast.makeText(context, "Saved Successfully!", Toast.LENGTH_SHORT).show()
                parentFragmentManager.popBackStack()
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }
}