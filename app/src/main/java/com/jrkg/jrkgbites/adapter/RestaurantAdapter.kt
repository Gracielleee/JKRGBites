package com.jrkg.jrkgbites.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorInflater
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrkg.jrkgbites.databinding.ItemRestaurantListCardBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.R
import android.util.Log // Keep Log import for future debugging
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import coil.load
import android.graphics.Color
import android.os.Vibrator
import androidx.core.graphics.ColorUtils
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import coil.request.CachePolicy

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
        holder.cardFlipContainer.z = 2f
        holder.cardFront.visibility = View.VISIBLE
        holder.cardFront.alpha = 1f
        holder.cardFront.rotationY = 0f
        holder.cardBack.visibility = View.GONE
        holder.cardBack.alpha = 0f
        holder.cardBack.rotationY = 180f
        holder.cardFlipContainer.animate().z(2f).setDuration(100).start()


        // 2. Set Front Info
        val displayName = restaurant.name ?: "Unknown Restaurant"
        holder.restaurantName.text = displayName
//        holder.restaurantDescription.text = restaurant.category ?: "N/A"

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
//        if (resId != 0) {
//            holder.restaurantImage.setImageResource(resId)
//        } else {
//            holder.restaurantImage.setImageResource(android.R.drawable.ic_menu_gallery)
//        }

        // Set card background color based on logo color using Coil and Palette
        holder.restaurantImage.load(resId) {
            crossfade(true)
            allowHardware(false) //Palette needs software bitmaps to read pixels
            memoryCachePolicy(CachePolicy.ENABLED)

            // Shadow settings
            val shadowRadius = 8f
            val shadowDx = 0f
            val shadowDy = 2f
            val shadowColor = Color.BLACK

            listener(
                onSuccess = { _, result ->
                    val bitmap = (result.drawable as BitmapDrawable).bitmap
                    Palette.from(bitmap).generate { palette ->
                        val swatch = palette?.mutedSwatch ?: palette?.darkVibrantSwatch
                        ?: palette?.dominantSwatch

                        swatch?.let {
                            val color = it.rgb
                            val glassyColor = ColorUtils.setAlphaComponent(color, 180)
                            holder.cardFront.setCardBackgroundColor(glassyColor)

                            // FORCE WHITE TEXT WITH SHADOW
                            holder.restaurantName.setTextColor(Color.WHITE)
                            holder.restaurantName.setShadowLayer(
                                shadowRadius,
                                shadowDx,
                                shadowDy,
                                shadowColor
                            )
                        }
                    }
                },
                onError = { _, _ -> //In case logo cannot be found
                    holder.restaurantImage.setImageResource(android.R.drawable.ic_menu_gallery)

                    val hash = restaurant.name.hashCode()
                    val hexColor = String.format("#%06X", (0xFFFFFF and hash))
                    val backColor = Color.parseColor(hexColor)

                    holder.cardFront.setCardBackgroundColor(backColor)

                    holder.restaurantName.setTextColor(Color.WHITE)
                    holder.restaurantName.setShadowLayer(
                        shadowRadius,
                        shadowDx,
                        shadowDy,
                        shadowColor
                    )
                }
            )
        }

        // 3. Set Back Info
        holder.backName.text = displayName
        holder.backCategory.text = "Category: ${restaurant.category ?: "N/A"}"
        holder.backCuisine.text = "Cuisine: ${restaurant.cuisine ?: "N/A"}"
        holder.backLevel.text = "Level: ${restaurant.level ?: "N/A"}"
        holder.backTags.text = "Tags: ${restaurant.tags?.joinToString(", ") ?: "N/A"}"

        //See details on RestaurantDetailsFragment on hold
        holder.cardFlipContainer.setOnLongClickListener() { view ->
            val restaurant = restaurantList[holder.adapterPosition]
            view.findNavController().navigate(
                R.id.to_restaurantDetailsFragment,
                bundleOf("restaurantId" to restaurant.id)
            )
            true
        }

        // 4. Conditional Click Logic (Flip OR onItemClick)
        holder.cardFlipContainer.setOnClickListener {
            val isFrontVisible = holder.cardFront.visibility == View.VISIBLE
            val visibleView = if (isFrontVisible) holder.cardFront else holder.cardBack
            val invisibleView = if (isFrontVisible) holder.cardBack else holder.cardFront

            holder.cardFlipContainer.animate().z(12f).setDuration(100).start()

            // 2. Setup the "Invisible" view to be ready to flip in
            invisibleView.visibility = View.VISIBLE
            invisibleView.alpha = 0f
            invisibleView.rotationY = if (isFrontVisible) 90f else -90f

            // 3. Animate the visible view OUT (to 90 degrees)
            visibleView.animate()
                .rotationY(if (isFrontVisible) -90f else 90f)
                .alpha(0f)
                .setDuration(250)
                .setInterpolator(android.view.animation.DecelerateInterpolator())
                .withEndAction {
                    visibleView.visibility = View.GONE

                    // 4. Animate the invisible view IN (from 90 to 0 degrees)
                    invisibleView.animate()
                        .rotationY(0f)
                        .alpha(1f)
                        .setDuration(250)
                        .setInterpolator(android.view.animation.OvershootInterpolator(1.5f))
                        .withEndAction {
                            holder.cardFlipContainer.animate().z(2f).setDuration(100).start()
                        }
                        .start()
                }
                .start()
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
//        val restaurantDescription = binding.restaurantDescription

        val backName = binding.backName
        val backCategory = binding.backCategory
        val backCuisine = binding.backCuisine
        val backLevel = binding.backLevel
        val backTags = binding.backTags
    }

}