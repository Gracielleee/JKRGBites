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
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.launch

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

        // 2. Observe Session and Stats reactively
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.sessionState.collect { user ->
                        binding.userNameText.text = user?.preferredName ?: "User"
                    }
                }

                launch {
                    viewModel.favoritesList.collect { list ->
                        binding.favCountText.text = list.size.toString()
                    }
                }

                launch {
                    viewModel.neverAgainList.collect { list ->
                        binding.neverAgainCountText.text = list.size.toString()
                    }
                }
            }
        }

        // 3. Setup Location Switch logic
        binding.locationSwitch.isChecked = viewModel.isProximityEnabled()
        binding.locationStatusText.text = if (binding.locationSwitch.isChecked) "Location Enabled" else "Location Disabled"

        binding.locationSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                // Show Warning Dialog when turning OFF
                com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Experience Note")
                    .setMessage("We want to make sure you get the best experience! By disabling location, we won't be able to show you exactly how close restaurants are or provide precise directions.")
                    .setPositiveButton("Understood") { _, _ ->
                        viewModel.setProximityEnabled(false)
                        binding.locationStatusText.text = "Location Disabled"
                    }
                    .setNegativeButton("Keep Enabled") { _, _ ->
                        // Revert the switch back to ON
                        buttonView.isChecked = true
                    }
                    .setCancelable(false)
                    .show()
            } else {
                viewModel.setProximityEnabled(true)
                binding.locationStatusText.text = "Location Enabled"
                val toastMsg = "Near-me suggestions active"
                Toast.makeText(requireContext(), toastMsg, Toast.LENGTH_SHORT).show()
            }
        }

        // 4. Logout Logic
        binding.logoutButton.setOnClickListener {
            lifecycleScope.launch {
                try {
                    viewModel.logout() // Log out the user
                    Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()

                    // Navigate to the LoginFragment and clear the back stack
                    findNavController().navigate(
                        R.id.loginFragment,
                        null,
                        NavOptions.Builder()
                            .setPopUpTo(R.id.nav_graph, true)
                            .build()
                    )
                    Toast.makeText(requireContext(), "Logging out...", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
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