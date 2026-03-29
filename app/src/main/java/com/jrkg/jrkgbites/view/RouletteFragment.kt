package com.jrkg.jrkgbites.view

import android.graphics.drawable.Animatable
import android.os.Bundle
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
import com.jrkg.jrkgbites.R
import com.jrkg.jrkgbites.databinding.FragmentRouletteBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import com.jrkg.jrkgbites.viewmodel.RouletteViewModel
import com.jrkg.jrkgbites.viewmodel.RouletteViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class RouletteFragment : Fragment() {

    private var _binding: FragmentRouletteBinding? = null
    private val binding get() = _binding!!
    
    private val rouletteViewModel: RouletteViewModel by viewModels {
        val userId = mainViewModel.sessionState.value?.id ?: "" 
        RouletteViewModelFactory(userId, mainViewModel.rouletteRepository)
    }
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

        // Check if navigation was triggered by a shake and a spin should be initiated
        arguments?.let { bundle ->
            val shouldSpin = bundle.getBoolean("shouldSpin", false)
            if (shouldSpin) {
                binding.root.post {
                    if (rouletteViewModel.canSpin() && displayList.isNotEmpty()) {
                        binding.btnSpin.performClick()
                    }
                }
            }
        }
    }

    private fun setupWheel() {
        viewLifecycleOwner.lifecycleScope.launch {
            // Updated to use favoritesList instead of deck to ensure only favorited restaurants are spun
            mainViewModel.favoritesList.collectLatest { favorites ->
                var filtered = favorites

                if (mainViewModel.isProximityEnabled()) {
                    val location = mainViewModel.getUserLocation()
                    val userLat = location.first
                    val userLng = location.second
                    val hasValidLocation = userLat != 0.0 || userLng != 0.0

                    if (hasValidLocation) {
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
                    } else {
                        Toast.makeText(context, "Proximity filter enabled, but location not available.", Toast.LENGTH_LONG).show()
                        filtered = favorites
                    }
                }

                if (filtered.isNotEmpty()) {
                    displayList = filtered.shuffled().take(12)
                    binding.spinWheelView.setRestaurants(displayList)
                } else if (favorites.isEmpty()) {
                    Toast.makeText(context, "No favorites to spin! Add some restaurants to your favorites first.", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(context, "No favorites nearby!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun fetchPersistentSession() {
        mainViewModel.refreshRouletteSession()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            // Main UI state observer: Handles Spin count, Button enablement, and Ad visibility
            launch {
                combine(
                    rouletteViewModel.spinsLeft,
                    mainViewModel.rouletteSession,
                    rouletteViewModel.isSpinning
                ) { spins, session, spinning ->
                    Triple(spins, session, spinning)
                }.collectLatest { (spins, session, spinning) ->
                    val adsWatched = session?.adsWatchedToday ?: 0
                    
                    if (spins > 0) {
                        binding.tvSpinsLeft.text = "Spins Left: $spins/${rouletteViewModel.maxSpins}"
                        binding.tvSpinsLeft.setTextColor(resources.getColor(R.color.md_theme_onSurface, null))
                        binding.spinWheelView.isGrayscale = false
                        binding.btnGetSpins.visibility = View.GONE
                    } else if (spinning) {
                        binding.tvSpinsLeft.text = "Good luck!"
                        binding.btnGetSpins.visibility = View.GONE
                    } else {
                        // Out of spins and not currently spinning
                        if (adsWatched < 2) {
                            binding.btnGetSpins.visibility = View.VISIBLE
                            binding.tvSpinsLeft.text = "Out of spins! Watch an ad for more."
                        } else {
                            binding.btnGetSpins.visibility = View.GONE
                            // Text update for midnight reset is handled in the second observer
                        }
                    }
                    
                    binding.btnSpin.isEnabled = spins > 0 && !spinning
                    binding.btnSpin.alpha = if (spinning) 0.5f else 1.0f
                }
            }

            // Midnight reset countdown observer
            launch {
                combine(rouletteViewModel.timeUntilReset, rouletteViewModel.spinsLeft, mainViewModel.rouletteSession) { timeStr, spins, session ->
                    Triple(timeStr, spins, session)
                }.collectLatest { (timeStr, spins, session) ->
                    if (spins == 0 && !rouletteViewModel.isSpinning.value) {
                        val adsWatched = session?.adsWatchedToday ?: 0
                        if (adsWatched >= 2) {
                            binding.tvSpinsLeft.text = "Out of spins! Reset in $timeStr"
                            binding.tvSpinsLeft.setTextColor(resources.getColor(R.color.md_theme_primary, null))
                            binding.spinWheelView.isGrayscale = true
                        }
                    }
                }
            }

            // Sync spinsLeft whenever rouletteSession changes, but ONLY if not currently spinning.
            // This prevents Firestore from overwriting the local decrement while the animation is running.
            launch {
                mainViewModel.rouletteSession.collectLatest { session ->
                    session?.let {
                        if (!rouletteViewModel.isSpinning.value) {
                            val remaining = (1 + it.adsWatchedToday) - it.spinsUsedToday
                            rouletteViewModel.setSpinsLeft(remaining.coerceIn(0, rouletteViewModel.maxSpins))
                        }
                    }
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

        binding.btnResetSpins.setOnClickListener {
            resetAllSpins()
        }

        // Show reset button only in debug builds
        val isDebug = (requireContext().applicationInfo.flags and android.content.pm.ApplicationInfo.FLAG_DEBUGGABLE) != 0
        binding.btnResetSpins.visibility = if (isDebug) View.VISIBLE else View.GONE
    }

    private fun showAdConfirmation() {
        com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
            .setTitle("Get Extra Spin")
            .setMessage("Watch a short ad to earn +1 spin? (Limit 2 additional spins per day)")
            .setPositiveButton("Watch") { _, _ -> simulateAd() }
            .setNegativeButton("Maybe later", null)
            .show()
    }

    private fun simulateAd() {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_simulated_ad, null)
        dialog.setContentView(view)

        val tvAdMessage = view.findViewById<TextView>(R.id.tv_ad_message)
        tvAdMessage.text = "Watching Ad... (5s)"

        val ivAnimation = view.findViewById<ImageView>(R.id.iv_ad_animation)
        val drawable = ivAnimation.drawable
        if (drawable is Animatable) {
            drawable.start()
        }

        dialog.setCancelable(false)
        dialog.show()

        viewLifecycleOwner.lifecycleScope.launch {
            for (i in 5 downTo 1) {
                tvAdMessage.text = "Watching Ad... (${i}s)"
                kotlinx.coroutines.delay(1000L)
            }
            tvAdMessage.text = "Ad complete! Awarding spin..."

            val success = mainViewModel.watchRouletteAd()
            if (success) {
                dialog.dismiss()
                rouletteViewModel.addSpinFromAd()
                Toast.makeText(context, "+1 Spin Awarded! \uD83C\uDF81", Toast.LENGTH_SHORT).show()
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
        Toast.makeText(context, "Directions feature coming soon! (ID: $restaurantId)", Toast.LENGTH_SHORT).show()
    }

    private fun resetAllSpins() {
        viewLifecycleOwner.lifecycleScope.launch {
            rouletteViewModel.resetSpins()
            val userId = mainViewModel.sessionState.value?.id
            if (userId != null) {
                mainViewModel.rouletteRepository.resetSpinSession(userId)
                Toast.makeText(context, "All spins reset for demo!", Toast.LENGTH_SHORT).show()
                fetchPersistentSession()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
