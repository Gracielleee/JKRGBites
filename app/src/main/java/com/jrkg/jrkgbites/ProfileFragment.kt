package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController // ADD THIS IMPORT
import com.jrkg.jrkgbites.databinding.FragmentProfileBinding
import com.jrkg.jrkgbites.viewmodel.MainViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Initialize ViewModel (Sharing data with MainActivity)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        // 2. Set the Stats (Favorites Count)
        // We filter the list from the ViewModel to see how many are favorites
        val favoriteCount = viewModel.favoritesList.value.count()
        binding.favCountText.text = favoriteCount.toString()

        val neverAgainCount = viewModel.neverAgainList.value.count()
        binding.neverAgainCountText.text = neverAgainCount.toString()

        // 3. Setup Location Switch logic
        binding.locationSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.locationStatusText.text = "Location Enabled"
                Toast.makeText(requireContext(), "Near-me suggestions active", Toast.LENGTH_SHORT).show()
            } else {
                binding.locationStatusText.text = "Location Disabled"
                Toast.makeText(requireContext(), "Showing all restaurants", Toast.LENGTH_SHORT).show()
            }
        }

        // 4. Logout Logic
        binding.logoutButton.setOnClickListener {
            // Log out the user (e.g., clear session data)
            viewModel.logout()

            // Navigate to the LoginFragment and clear the back stack
            findNavController().navigate(
                R.id.loginFragment,
                null,
                NavOptions.Builder()
                    .setPopUpTo(R.id.nav_graph,
                        true)
                    .build()
            )
            Toast.makeText(requireContext(),
                "Logging out...",
                Toast.LENGTH_SHORT).show()
        }

        // 5. Navigate to Restaurant Ratings
        binding.manageRatingsCard.setOnClickListener {
            findNavController().navigate(R.id.action_nav_profile_to_restaurantRatingFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}