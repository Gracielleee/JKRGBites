package com.jrkg.jrkgbites

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jrkg.jrkgbites.databinding.FragmentRouletteBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import com.jrkg.jrkgbites.viewmodel.RouletteViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RouletteFragment : Fragment() {

    private var _binding: FragmentRouletteBinding? = null
    private val binding get() = _binding!!
    
    private val rouletteViewModel: RouletteViewModel by viewModels()
    private lateinit var mainViewModel: MainViewModel
    
    private var displayList: List<Restaurant> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRouletteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        setupWheel()
        fetchPersistentSession()
        observeViewModel()
        setupListeners()
    }

    private fun setupWheel() {
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.favoritesList.collectLatest { favorites ->
                var filtered = favorites

                if (mainViewModel.isProximityEnabled()) {
                    val (userLat, userLng) = mainViewModel.getUserLocation()
                    if (userLat != 0.0 && userLng != 0.0) {
                        filtered = favorites.filter { restaurant ->
                            val resLat = restaurant.lat?.toDoubleOrNull() ?: 0.0
                            val resLng = restaurant.lng?.toDoubleOrNull() ?: 0.0
                            if (resLat != 0.0 && resLng != 0.0) {
                                val results = FloatArray(1)
                                android.location.Location.distanceBetween(userLat, userLng, resLat, resLng, results)
                                results[0] <= 5000 // 5km radius limit
                            } else {
                                true
                            }
                        }
                    }
                }

                if (filtered.isNotEmpty()) {
                    // Shuffle and take up to 12 restaurants to keep the wheel readable
                    displayList = filtered.shuffled().take(12)
                    binding.spinWheelView.setRestaurants(displayList)
                } else if (favorites.isEmpty()) {
                    Toast.makeText(context, "No favorites to spin!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(context, "No favorites nearby!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun fetchPersistentSession() {
        viewLifecycleOwner.lifecycleScope.launch {
            val session = mainViewModel.getRouletteSession()
            session?.let {
                // Calculate remaining: 1 free + ads watched - spins used
                val remaining = (1 + it.adsWatchedToday) - it.spinsUsedToday
                rouletteViewModel.setSpinsLeft(remaining.coerceAtLeast(0))
            }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                rouletteViewModel.spinsLeft.collectLatest { count ->
                    if (count > 0) {
                        binding.tvSpinsLeft.text = "Spins Left: $count/${rouletteViewModel.maxSpins}"
                        binding.tvSpinsLeft.setTextColor(resources.getColor(R.color.md_theme_onSurface, null))
                        binding.spinWheelView.isGrayscale = false
                    } else {
                        binding.spinWheelView.isGrayscale = true
                    }
                    
                    binding.btnGetSpins.visibility = if (count == 0 && rouletteViewModel.maxSpins > 1) View.VISIBLE else View.GONE
                    binding.btnSpin.isEnabled = count > 0
                }
            }

            launch {
                rouletteViewModel.timeUntilReset.collectLatest { timeStr ->
                    if (rouletteViewModel.spinsLeft.value == 0) {
                        binding.tvSpinsLeft.text = "Out of spins! Reset in $timeStr"
                        binding.tvSpinsLeft.setTextColor(resources.getColor(R.color.md_theme_primary, null))
                        binding.btnGetSpins.visibility = View.GONE // Hide ad button if truly at limit
                    }
                }
            }

            launch {
                rouletteViewModel.isSpinning.collectLatest { spinning ->
                    binding.btnSpin.alpha = if (spinning) 0.5f else 1.0f
                    binding.btnSpin.isEnabled = !spinning && rouletteViewModel.spinsLeft.value > 0
                }
            }
        }
    }

    private fun setupListeners() {
        binding.btnSpin.setOnClickListener {
            if (rouletteViewModel.canSpin() && displayList.isNotEmpty()) {
                val winningIndex = (0 until displayList.size).random()
                rouletteViewModel.startSpin()
                
                binding.spinWheelView.spinTo(winningIndex) {
                    val winner = displayList[winningIndex]
                    
                    // Save to Firestore!
                    viewLifecycleOwner.lifecycleScope.launch {
                        mainViewModel.logRouletteSpin(winner.id)
                    }

                    rouletteViewModel.onSpinFinished()
                    showWinner(winner)
                }
            }
        }

        binding.spinWheelView.setOnClickListener {
            binding.spinWheelView.fastForward()
        }

        binding.btnGetSpins.setOnClickListener {
            showAdConfirmation()
        }
    }

    private fun showAdConfirmation() {
        com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
            .setTitle("Get Extra Spin")
            .setMessage("Watch a short ad to earn +1 spin? (Limit 3 per day)")
            .setPositiveButton("Watch") { _, _ -> simulateAd() }
            .setNegativeButton("Maybe later", null)
            .show()
    }

    private fun simulateAd() {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_simulated_ad, null)
        dialog.setContentView(view)

        // Start the custom spinning logo animation
        val ivAnimation = view.findViewById<ImageView>(R.id.iv_ad_animation)
        val drawable = ivAnimation.drawable
        if (drawable is Animatable) {
            drawable.start()
        }

        dialog.setCancelable(false)
        dialog.show()

        viewLifecycleOwner.lifecycleScope.launch {
            val success = mainViewModel.watchRouletteAd()
            if (success) {
                dialog.dismiss()
                rouletteViewModel.addSpinFromAd()
                Toast.makeText(context, "+1 Spin Awarded! 🎁", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showWinner(restaurant: Restaurant) {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_roulette_winner, null)
        
        view.findViewById<TextView>(R.id.tv_winner_name).text = restaurant.name
        view.findViewById<TextView>(R.id.tv_winner_category).text = restaurant.category ?: "Restaurant"
        
        view.findViewById<Button>(R.id.btn_go_to_details).setOnClickListener {
            dialog.dismiss()
            val bundle = Bundle().apply {
                putString("restaurantId", restaurant.id)
            }
            findNavController().navigate(R.id.restaurantDetailsFragment, bundle)
        }

        view.findViewById<Button>(R.id.btn_directions).setOnClickListener {
            onDirectionsClick(restaurant.id)
        }

        dialog.setContentView(view)
        dialog.show()
    }

    private fun onDirectionsClick(restaurantId: String) {
        // Placeholder stub for Maps API integration
        Toast.makeText(context, "Directions feature coming soon! (ID: $restaurantId)", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
