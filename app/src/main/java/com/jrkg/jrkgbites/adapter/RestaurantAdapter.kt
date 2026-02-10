package com.jrkg.jrkgbites.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorInflater
import android.content.Context
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrkg.jrkgbites.databinding.ItemRestaurantListCardBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.R
import android.util.Log // Keep Log import for future debugging

class RestaurantAdapter(
    private val context: Context,
    private var restaurantList: List<Restaurant>,
    private val onItemClick: ((Restaurant) -> Unit)? = null // Optional click listener
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    fun updateList(newList: List<Restaurant>) {
        this.restaurantList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = ItemRestaurantListCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.root.isClickable = true // Ensure the entire item view is clickable
        binding.root.isFocusable = true // Ensure the entire item view is focusable
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurantList[position]

        // 1. Reset card state for View Recycling
        holder.cardFront.visibility = View.VISIBLE
        holder.cardFront.alpha = 1f
        holder.cardBack.visibility = View.GONE
        holder.cardBack.alpha = 0f

        // 2. Set Front Info
        val displayName = restaurant.name ?: "Unknown Restaurant"
        holder.restaurantName.text = displayName
        holder.restaurantDescription.text = restaurant.category ?: "N/A"

        // Logo Logic: matches "Ajisen Ramen" to "ajisenramen" (no underscores)
        val normalizedDisplayName = displayName.lowercase().replace("'", "").replace("’", "")

        // Generate resource name by removing special characters and spaces
        var resourceName = normalizedDisplayName
            .replace(" ", "")
            .replace("-", "")
            .replace(".", "")
            .replace("&", "")
            .replace(",", "")
            .replace("!", "")
            .replace("?", "")
            .replace("/", "")
        
        val resId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)
        if (resId != 0) {
            holder.restaurantImage.setImageResource(resId)
        } else {
            holder.restaurantImage.setImageResource(android.R.drawable.ic_menu_gallery)
        }

        // 3. Set Back Info
        holder.backName.text = displayName
        holder.backCategory.text = "Category: ${restaurant.category ?: "N/A"}"
        holder.backCuisine.text = "Cuisine: ${restaurant.cuisine ?: "N/A"}"
        holder.backLevel.text = "Level: ${restaurant.level ?: "N/A"}"
        holder.backTags.text = "Tags: ${restaurant.tags?.joinToString(", ") ?: "N/A"}"

        // 4. Conditional Click Logic (Flip OR onItemClick)
        holder.cardFlipContainer.setOnClickListener { // Use container to handle click for both sides
            Log.d("CardFlipDebug", "Card clicked for restaurant: ${restaurant.name}") // ADDED DEBUG LOG
            if (onItemClick != null) {
                // If an onItemClick listener is provided (e.g., for selection in RestaurantRatingFragment)
                onItemClick.invoke(restaurant)
            } else {
                // If no onItemClick listener (e.g., for Home/Search fragments), perform card flip
                val frontAnim = AnimatorInflater.loadAnimator(holder.itemView.context, R.animator.card_flip_right_out)
                val backAnim = AnimatorInflater.loadAnimator(holder.itemView.context, R.animator.card_flip_right_in)

                val scale = holder.itemView.context.resources.displayMetrics.density
                holder.cardFlipContainer.cameraDistance = 8000 * scale

                if (holder.cardFront.visibility == View.VISIBLE) {
                    frontAnim.setTarget(holder.cardFront)
                    backAnim.setTarget(holder.cardBack)

                    // Listeners to control visibility precisely
                    frontAnim.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            holder.cardFront.visibility = View.GONE
                        }
                    })
                    backAnim.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationStart(animation: Animator) {
                            holder.cardBack.visibility = View.VISIBLE
                        }
                    })

                    frontAnim.start()
                    backAnim.start()
                } else {
                    val frontAnimReverse = AnimatorInflater.loadAnimator(holder.itemView.context, R.animator.card_flip_left_out)
                    val backAnimReverse = AnimatorInflater.loadAnimator(holder.itemView.context, R.animator.card_flip_left_in)

                    frontAnimReverse.setTarget(holder.cardBack)
                    backAnimReverse.setTarget(holder.cardFront)

                    // Listeners to control visibility precisely for reverse flip
                    frontAnimReverse.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            holder.cardBack.visibility = View.GONE
                        }
                    })
                    backAnimReverse.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationStart(animation: Animator) {
                            holder.cardFront.visibility = View.VISIBLE
                        }
                    })

                    frontAnimReverse.start()
                    backAnimReverse.start()
                }
            }
        }

    }

    override fun getItemCount(): Int = restaurantList.size

    class RestaurantViewHolder(private val binding: ItemRestaurantListCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val cardFlipContainer = binding.cardFlipContainer
        val cardFront = binding.cardFront
        val cardBack = binding.cardBack

        val restaurantImage = binding.restaurantImage
        val restaurantName = binding.restaurantName
        val restaurantDescription = binding.restaurantDescription

        val backName = binding.backName
        val backCategory = binding.backCategory
        val backCuisine = binding.backCuisine
        val backLevel = binding.backLevel
        val backTags = binding.backTags
    }
}