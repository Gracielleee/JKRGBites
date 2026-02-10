package com.jrkg.jrkgbites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrkg.jrkgbites.databinding.ItemRestaurantRatingBinding
import com.jrkg.jrkgbites.model.RestaurantRating

class RestaurantRatingAdapter(
    private val onRatingUpdate: (RestaurantRating) -> Unit,
    private val onRatingDelete: (RestaurantRating) -> Unit
) : ListAdapter<RestaurantRating, RestaurantRatingAdapter.RatingViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val binding = ItemRestaurantRatingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RatingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        // Implement update and delete logic here if needed, for example:
        // holder.itemView.setOnClickListener { onRatingUpdate(current) }
    }

    class RatingViewHolder(private val binding: ItemRestaurantRatingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(rating: RestaurantRating) {
            binding.restaurantIdTextView.text = rating.restaurantId
            binding.commentTextView.text = rating.comment
            binding.ratingBar.rating = rating.rating.toFloat()
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<RestaurantRating>() {
            override fun areItemsTheSame(oldItem: RestaurantRating, newItem: RestaurantRating):
                    Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RestaurantRating, newItem: RestaurantRating):
                    Boolean {
                return oldItem == newItem
            }
        }
    }
}
