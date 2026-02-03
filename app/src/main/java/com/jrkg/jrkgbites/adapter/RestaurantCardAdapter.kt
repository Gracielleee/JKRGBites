package com.jrkg.jrkgbites.adapter

import android.animation.AnimatorInflater
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrkg.jrkgbites.databinding.ItemRestaurantListCardBinding
import com.jrkg.jrkgbites.model.Restaurant
import com.jrkg.jrkgbites.R
import android.util.Log
import java.text.Normalizer
import java.text.Normalizer.Form

class RestaurantCardAdapter(private val restaurants: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantCardAdapter.RestaurantCardViewHolder>() {

    // Manual mapping for restaurant display names to drawable resource names
    private val logoMap = mapOf(
        "blue seas seafood" to "blue_seas",
        "starbucks" to "star_bucks",
        "kyukyu ramen 99" to "kyukyu_ramen",
        "bonchon chicken" to "bon_chon",
        "sambo kojin" to "sambokojin",
        "contis bakeshop" to "contis",
        "yabu house of katsu" to "yabu",
        "mary grace cafe" to "mary_grace",
        "jd g italian food" to "jd_g_italian",
        "bash cafe" to "bash_cafe",
        "pineapple hill cuisine" to "pineapple_hills_kitchen",
        "mama lous italian kitchen" to "mama_lous",
        "mantra indian cuisine" to "mantra_indian_kitchen",
        "asakusa home of tempura" to "asakusa",
        "wang fu chinese cafe" to "wangfu",
        "nabe japanese izakaya" to "nabe",
        "ucc clockwork" to "ucc",
        "paper moon cafe" to "paper_moon",
        "misto" to "misto_seda",
        "jco donuts coffee" to "jco_donuts",
        "kenny rogers roasters" to "kenny_rogers",
        "chowking" to "chow_king",
        "ramen nagi" to "ramenagi",
        "cyma greek taverna" to "cyma_greek",
        "salad stop" to "salad_stop",
        "bigoli" to "big_oli",
        "100 gadz parathas" to "img_100_gadz", // Specific mapping for this logo
        // New entries for recently reported missing logos
        "panublion" to "panublion",
        "lantawan" to "lantawan",
        "joe ra cafe" to "joe_ra_cafe",
        "sunbird ridge" to "sunbird_ridge",
        "kawayan cafe" to "kawayan_cafe",
        "hill brews coffee" to "hill_brews_coffee",
        "sea bean coffee house" to "sea_bean_coffee_house",
        "chloes lechon" to "chloes_lechon",
        "botejyu" to "botejyu",
        "gerrys restaurant cafe" to "gerrys_grill",
        "giligans island" to "giligan",
        "armynavy" to "army_navy",
        "yellowcab pizza co" to "yellow_cab",
        // The following entries are commented out because their corresponding drawable files were not found
        // "seaport bar grill" to "seaport_bar_grill",
        // "big cheff pizza" to "big_cheff_pizza",
        // "gangnam grill" to "gangnam_grill",
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantCardViewHolder {
        val binding = ItemRestaurantListCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantCardViewHolder, position: Int) {
        val restaurant = restaurants[position]

        // 1. Reset card state for View Recycling
        holder.cardFront.visibility = View.VISIBLE
        holder.cardFront.alpha = 1f
        holder.cardBack.visibility = View.GONE
        holder.cardBack.alpha = 0f

        // 2. Set Front Info
        val displayName = restaurant.name ?: "Unknown Restaurant"
        holder.restaurantName.text = displayName
        holder.restaurantDescription.text = restaurant.category ?: "N/A"

        // Logo Logic: matches "Ajisen Ramen" to "ajisen_ramen"
        val normalizedDisplayName = displayName.lowercase().replace("'", "").replace("\u2019", "")
        var resourceName = logoMap[normalizedDisplayName]

        Log.d("LogoDebug", "Processing: $displayName")
        Log.d("LogoDebug", "Normalized (for map lookup): $normalizedDisplayName")
        Log.d("LogoDebug", "Mapped ResourceName (if any): $resourceName")

        if (resourceName == null) {
            // If not in map, generate from normalizedDisplayName
            val asciiFoldedName = Normalizer.normalize(normalizedDisplayName, Normalizer.Form.NFD)
                .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "") // Removes accents
                .replace(Regex("[^a-z0-9]+"), "_") // Replace any sequence of non-alphanumeric with a single underscore
                .removePrefix("_") // Remove leading underscore if any
                .removeSuffix("_") // Remove trailing underscore if any
            resourceName = asciiFoldedName
            Log.d("LogoDebug", "Generated ResourceName: $resourceName")
        }

        // Ensure resource name starts with a letter, by prepending "img_" if it starts with a digit.
        // This is primarily for drawable resources like "100_gadz.jpg"
        if (resourceName.isNotEmpty() && resourceName[0].isDigit()) {
            resourceName = "img_$resourceName"
            Log.d("LogoDebug", "Final ResourceName (prepended img_): $resourceName")
        }
        
        Log.d("LogoDebug", "Searching for resource: $resourceName")
        val resId = holder.itemView.context.resources.getIdentifier(resourceName, "drawable", holder.itemView.context.packageName)
        Log.d("LogoDebug", "resId found: $resId (0 means not found)")
        if (resId != 0) {
            holder.restaurantImage.setImageResource(resId)
        } else {
            holder.restaurantImage.setImageResource(android.R.drawable.ic_menu_gallery)
        }

        // 3. Set Back Info - Fixed to use the variables in your Restaurant.kt
        holder.backName.text = displayName
        holder.backCategory.text = "Category: ${restaurant.category ?: "N/A"}"
        holder.backCuisine.text = "Cuisine: ${restaurant.cuisine ?: "N/A"}"
        holder.backLevel.text = "Level: ${restaurant.level ?: "N/A"}"
        holder.backTags.text = "Tags: ${restaurant.tags?.joinToString(", ") ?: "N/A"}"

        // 4. Flip Animation Logic
        holder.cardFront.setOnClickListener {
            val frontAnim = AnimatorInflater.loadAnimator(holder.itemView.context, R.animator.card_flip_right_out)
            val backAnim = AnimatorInflater.loadAnimator(holder.itemView.context, R.animator.card_flip_right_in)

            frontAnim.setTarget(holder.cardFront)
            backAnim.setTarget(holder.cardBack)

            frontAnim.start()
            backAnim.start()
        }

        holder.cardBack.setOnClickListener {
            val frontAnim = AnimatorInflater.loadAnimator(holder.itemView.context, R.animator.card_flip_left_out)
            val backAnim = AnimatorInflater.loadAnimator(holder.itemView.context, R.animator.card_flip_left_in)

            frontAnim.setTarget(holder.cardBack)
            backAnim.setTarget(holder.cardFront)

            frontAnim.start()
            backAnim.start()
        }
    }

    override fun getItemCount(): Int = restaurants.size

    class RestaurantCardViewHolder(private val binding: ItemRestaurantListCardBinding) :
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
