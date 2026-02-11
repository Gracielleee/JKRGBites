package com.jrkg.jrkgbites

import android.animation.AnimatorInflater
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jrkg.jrkgbites.databinding.FragmentPickerBinding
import com.jrkg.jrkgbites.domain.SwipeDirection
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.viewmodel.MainViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class PickerFragment : Fragment() {

    private var _binding: FragmentPickerBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private var currentRestaurant: Restaurant? = null
    private lateinit var gestureDetector: GestureDetectorCompat
    private var isAnimating = false
    private var isNavigating = false // Flag to prevent deck updates during navigation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        setupGestureDetection()
        observeRestaurantDeck()
        observeSelectedRestaurant() // New observer call

        // Set up initial UI (Menu and Filter icons)
        binding.pickerMenuIcon.setOnClickListener { /* TODO: Implement menu logic */ }
        binding.pickerFilterIcon.setOnClickListener { /* TODO: Implement filter logic */ }

        // Removed single-tap card flip logic from here, it's now handled by onDoubleTap
    }

    private fun setupGestureDetection() {
        gestureDetector = GestureDetectorCompat(requireContext(), object : GestureDetector.SimpleOnGestureListener() {
            private val SWIPE_THRESHOLD = 100
            private val SWIPE_VELOCITY_THRESHOLD = 100
            private val ROTATION_MAGNITUDE = 15f // Degrees

            override fun onDown(e: MotionEvent): Boolean {
                // Reset card position if it was dragged before a proper fling
                binding.pickerCard.clearAnimation()
                binding.pickerCard.translationX = 0f
                binding.pickerCard.translationY = 0f
                binding.pickerCard.rotation = 0f
                return true // Important to return true to receive subsequent events
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                if (isAnimating || isNavigating || e1 == null) return false

                // Drag the card visually
                val deltaX = e2.x - e1.x
                val deltaY = e2.y - e1.y

                binding.pickerCard.translationX = deltaX
                binding.pickerCard.translationY = deltaY

                // Optional: Add slight rotation effect during drag
                val rotation = deltaX / binding.pickerCard.width * ROTATION_MAGNITUDE
                binding.pickerCard.rotation = rotation

                return true
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                if (isAnimating || isNavigating || e1 == null) return false

                val diffX = e2.x - e1.x
                val diffY = e2.y - e1.y

                var handled = false

                // Horizontal swipe
                if (Math.abs(diffX) > Math.abs(diffY) &&
                    Math.abs(diffX) > SWIPE_THRESHOLD &&
                    Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD
                ) {
                    if (diffX > 0) { // Swipe Right
                        animateCardOffScreen(SwipeDirection.RIGHT)
                    } else { // Swipe Left
                        animateCardOffScreen(SwipeDirection.LEFT)
                    }
                    handled = true
                }
                // Vertical swipe
                else if (Math.abs(diffY) > SWIPE_THRESHOLD &&
                    Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD
                ) {
                    if (diffY < 0) { // Swipe Up
                        animateCardOffScreen(SwipeDirection.UP)
                    } else { // Swipe Down
                        animateCardOffScreen(SwipeDirection.DOWN)
                    }
                    handled = true
                }

                if (!handled) {
                    // If not a recognized swipe, animate back to center
                    resetCardPositionWithAnimation()
                }
                return handled
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                currentRestaurant?.let {
                    if (!isAnimating && !isNavigating) { // Prevent flip during swipe/nav animation
                        flipCard()
                    }
                }
                return true
            }
        })

        binding.pickerCard.setOnTouchListener { v, event ->
            // Pass touch events to GestureDetectorCompat
            gestureDetector.onTouchEvent(event)

            // If it's an UP event and no fling was detected, reset position
            if (event.action == MotionEvent.ACTION_UP && !isAnimating && !isNavigating) {
                resetCardPositionWithAnimation()
            }
            true // Consume event
        }
    }

    private fun animateCardOffScreen(direction: SwipeDirection) {
        if (isAnimating) return
        isAnimating = true

        val card = binding.pickerCard
        val screenWidth = resources.displayMetrics.widthPixels.toFloat()
        val screenHeight = resources.displayMetrics.heightPixels.toFloat()

        val targetX: Float
        val targetY: Float
        val targetRotation: Float
        var toastMessage: String

        when (direction) {
            SwipeDirection.RIGHT -> {
                targetX = screenWidth
                targetY = 0f
                targetRotation = 30f
                toastMessage = "Restaurant Selected!"
            }
            SwipeDirection.LEFT -> {
                targetX = -screenWidth
                targetY = 0f
                targetRotation = -30f
                toastMessage = "Restaurant Discarded."
            }
            SwipeDirection.UP -> {
                targetX = 0f
                targetY = -screenHeight
                targetRotation = 0f
                toastMessage = "Added to Favorites!"
            }
            SwipeDirection.DOWN -> {
                targetX = 0f
                targetY = screenHeight
                targetRotation = 0f
                toastMessage = "Added to Never Again."
            }
        }

        card.animate()
            .translationX(targetX)
            .translationY(targetY)
            .rotation(targetRotation)
            .setDuration(300)
            .withEndAction {
                currentRestaurant?.let { restaurant ->
                    viewModel.onSwipe(restaurant, direction)
                    Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
                }
                if (direction != SwipeDirection.RIGHT) { // Only load next if not navigating away
                    resetCardVisuals() // Just reset visuals, observeDeck handles next
                } else {
                    // For SwipeRight, _selectedRestaurant is updated and observer will handle navigation
                    // Don't reset visuals yet, let the navigation hide this fragment
                    isAnimating = false // Reset animation flag
                }
            }
            .start()
    }

    private fun resetCardVisuals() { // Renamed from resetCardPositionAndLoadNext
        val card = binding.pickerCard
        card.translationX = 0f
        card.translationY = 0f
        card.rotation = 0f
        card.alpha = 0f // Make it invisible temporarily until new data comes
        isAnimating = false
    }

    private fun resetCardPositionWithAnimation() {
        binding.pickerCard.animate()
            .translationX(0f)
            .translationY(0f)
            .rotation(0f)
            .setDuration(150)
            .start()
    }

    private fun observeRestaurantDeck() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.deck.collect { deck ->
                if (!isNavigating) { // Only update if not currently navigating
                    if (deck.isNotEmpty()) {
                        val nextRestaurant = deck.first()
                        currentRestaurant = nextRestaurant
                        updateCardUI(currentRestaurant!!)
                        // Animate fade-in for the new card
                        binding.pickerCard.animate().alpha(1f).setDuration(200).start()
                        binding.pickerCard.visibility = View.VISIBLE
                    } else {
                        currentRestaurant = null
                        binding.pickerCard.visibility = View.GONE
                        Toast.makeText(requireContext(), "No more restaurants in the deck!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun updateCardUI(restaurant: Restaurant) {
        // Reset card to front view
        binding.pickerCardFlipContainer.rotationY = 0f
        binding.pickerCardFront.visibility = View.VISIBLE
        binding.pickerCardBackLayout.visibility = View.GONE


        binding.pickerCardName.text = restaurant.name.orEmpty()
        binding.pickerCardCategory.text = "${restaurant.cuisine.orEmpty()} • ${restaurant.category.orEmpty()}"
        binding.pickerCardLocation.text = restaurant.level.orEmpty()
        binding.pickerCardDetails.text = restaurant.tags?.joinToString(", ").orEmpty() // Assuming picker_card_details

        // Load image using the explicit logoResourceName
        val resId = if (!restaurant.logoResourceName.isNullOrEmpty()) {
            context?.resources?.getIdentifier(restaurant.logoResourceName, "drawable", context?.packageName) ?: 0
        } else {
            0
        }

        if (resId != 0) {
            binding.pickerCardFront.setImageResource(resId)
        } else {
            binding.pickerCardFront.setImageResource(android.R.drawable.ic_menu_gallery)
        }
    }

        private fun flipCard() {

            val scale = requireContext().resources.displayMetrics.density

            binding.pickerCardFlipContainer.cameraDistance = 8000 * scale

    

            val flipOutAnimator = AnimatorInflater.loadAnimator(requireContext(), R.animator.card_flip_right_out)

            val flipInAnimator = AnimatorInflater.loadAnimator(requireContext(), R.animator.card_flip_right_in)

    

            if (binding.pickerCardFront.visibility == View.VISIBLE) {

                flipOutAnimator.setTarget(binding.pickerCardFront)

                flipInAnimator.setTarget(binding.pickerCardBackLayout)

                flipOutAnimator.start()

                flipInAnimator.start()

                // This visibility change ensures the back is fully visible after the animation

                binding.pickerCardFront.postDelayed({ binding.pickerCardFront.visibility = View.GONE }, 250)

                binding.pickerCardBackLayout.visibility = View.VISIBLE

            } else {

                flipOutAnimator.setTarget(binding.pickerCardBackLayout)

                flipInAnimator.setTarget(binding.pickerCardFront)

                flipOutAnimator.start()

                flipInAnimator.start()

                // This visibility change ensures the front is fully visible after the animation

                binding.pickerCardBackLayout.postDelayed({ binding.pickerCardBackLayout.visibility = View.GONE }, 250)

                binding.pickerCardFront.visibility = View.VISIBLE

            }

        }

    

        private fun observeSelectedRestaurant() {

            viewLifecycleOwner.lifecycleScope.launch {

                viewModel.selectedRestaurant.filterNotNull().collect { selectedRestaurant ->

                    // This block is triggered when _selectedRestaurant is set (i.e., on SwipeDirection.RIGHT)

                    if (!isNavigating && !isAnimating) { // Prevent multiple navigations/conflicts

                        isNavigating = true // Set flag to prevent deck updates

                        currentRestaurant = null // Clear current restaurant

                        binding.pickerCard.visibility = View.GONE // Hide the picker card immediately

    

                        val action = PickerFragmentDirections.actionNavPickerToRestaurantDetailsFragment(selectedRestaurant.id!!)

                        findNavController().navigate(action)

    

                        // Reset selected restaurant in ViewModel after navigation (important for subsequent selections)

                        viewModel.clearSelectedRestaurant()

                        isNavigating = false // Reset navigation flag

                    }

                }

            }

        }

    

        override fun onDestroyView() {

            super.onDestroyView()

            _binding = null

        }
}