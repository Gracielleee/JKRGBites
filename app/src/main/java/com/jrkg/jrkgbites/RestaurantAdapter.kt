package com.jrkg.jrkgbites

import android.animation.AnimatorInflater
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jrkg.jrkgbites.model.Restaurant

class RestaurantAdapter(
    private val context: Context,
    private var restaurantList: List<Restaurant>
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: FrameLayout = view.findViewById(R.id.card_flip_container)
        val cardFront: LinearLayout = view.findViewById(R.id.card_front)
        val cardBack: LinearLayout = view.findViewById(R.id.card_back)
        val frontName: TextView = view.findViewById(R.id.front_name)
        val frontLogo: ImageView = view.findViewById(R.id.front_logo)
        val backName: TextView = view.findViewById(R.id.back_name)
        val backCategory: TextView = view.findViewById(R.id.back_category)
        val backDetails: TextView = view.findViewById(R.id.back_details)
        val backLocation: TextView = view.findViewById(R.id.back_location)
    }

    fun updateList(newList: List<Restaurant>) {
        this.restaurantList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant_card, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val data = restaurantList[position]

        // FIX: Use 'name' (lowercase) to match your data class variable
        val displayName = data.name ?: "Unknown Restaurant"

        // 1. Reset card state for View Recycling
        holder.cardFront.visibility = View.VISIBLE
        holder.cardFront.alpha = 1f
        holder.cardBack.visibility = View.GONE
        holder.cardBack.alpha = 0f

        // 2. Set Front Info
        holder.frontName.text = displayName

        // Logo Logic: matches "Ajisen Ramen" to "ajisen_ramen"
        val resourceName = displayName.lowercase()
            .replace(" ", "_")
            .replace("-", "_")
            .replace(".", "")
            .replace("&", "")
            .replace("'", "")
            .replace("\u2019", "")

        val resId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)
        if (resId != 0) {
            holder.frontLogo.setImageResource(resId)
        } else {
            holder.frontLogo.setImageResource(android.R.drawable.ic_menu_gallery)
        }

        // 3. Set Back Info - Fixed to use the variables in your Restaurant.kt
        holder.backName.text = displayName
        holder.backCategory.text = "${data.cuisine ?: "N/A"} • ${data.category ?: "N/A"}"
        holder.backLocation.text = data.level ?: "No Level Info"
        holder.backDetails.text = data.tags?.joinToString(", ") ?: "No tags"

        // 4. Flip Animation Logic
        holder.container.setOnClickListener {
            val frontAnim = AnimatorInflater.loadAnimator(context, R.animator.card_flip_front)
            val backAnim = AnimatorInflater.loadAnimator(context, R.animator.card_flip_back)

            if (holder.cardFront.visibility == View.VISIBLE) {
                frontAnim.setTarget(holder.cardFront)
                backAnim.setTarget(holder.cardBack)

                holder.cardBack.visibility = View.VISIBLE
                holder.cardBack.alpha = 1f
                frontAnim.start()
                backAnim.start()

                holder.cardFront.postDelayed({ holder.cardFront.visibility = View.GONE }, 250)
            } else {
                frontAnim.setTarget(holder.cardBack)
                backAnim.setTarget(holder.cardFront)

                holder.cardFront.visibility = View.VISIBLE
                holder.cardFront.alpha = 1f
                frontAnim.start()
                backAnim.start()

                holder.cardBack.postDelayed({ holder.cardBack.visibility = View.GONE }, 250)
            }
        }
    }

    override fun getItemCount(): Int = restaurantList.size
}