package com.jrkg.jrkgbites.domain

import com.jrkg.jrkgbites.data.RestaurantDao
import com.jrkg.jrkgbites.data.RestaurantRepository

class RestaurantManager(
    private val restaurantDao: RestaurantDao,
    private val restaurantRepository: RestaurantRepository
) {

    // REMOTE
    suspend fun toggleFavorite(restaurantId: String, userId: String) {
        if (restaurantRepository.isFavorited(restaurantId, userId)) {
            restaurantRepository.removeFromFavorites(restaurantId, userId)
        } else {
            if (restaurantRepository.isNeverAgain(restaurantId, userId)) {
                restaurantRepository.removeFromNeverAgain(restaurantId, userId)  // Can't be both
            }
            restaurantRepository.addToFavorites(restaurantId, userId)
        }
    }

    suspend fun toggleNeverAgain(restaurantId: String, userId: String) {
        if (restaurantRepository.isNeverAgain(restaurantId, userId)) {
            restaurantRepository.removeFromNeverAgain(restaurantId, userId)
        } else {
            if (restaurantRepository.isFavorited(restaurantId, userId)) {
                restaurantRepository.removeFromFavorites(restaurantId, userId)  // Can't be both
            }
            restaurantRepository.addToNeverAgain(restaurantId, userId)
        }
    }

    suspend fun addToNeverAgain(restaurantId: String, userId: String) {
        if (restaurantRepository.isFavorited(restaurantId, userId)) {
            restaurantRepository.removeFromFavorites(restaurantId, userId)  // Can't be both
        }
        restaurantRepository.addToNeverAgain(restaurantId, userId)
    }

    // LOCAL
    suspend fun toggleFavoriteLocal(restaurantId: String) {
        if (restaurantRepository.isFavoritedInLocal(restaurantId)) {
            restaurantRepository.removeFromFavoritesLocal(restaurantId)
        } else {
            if (restaurantRepository.isNeverAgainInLocal(restaurantId)) {
                restaurantRepository.removeFromNeverAgainLocal(restaurantId)  // Can't be both
            }
            restaurantRepository.addToFavoritesLocal(restaurantId)
        }
    }

    suspend fun toggleNeverAgainLocal(restaurantId: String) {
        if (restaurantRepository.isNeverAgainInLocal(restaurantId)) {
            restaurantRepository.removeFromNeverAgainLocal(restaurantId)
        } else {
            if (restaurantRepository.isFavoritedInLocal(restaurantId)) {
                restaurantRepository.removeFromFavoritesLocal(restaurantId)  // Can't be both
            }
            restaurantRepository.addToNeverAgainLocal(restaurantId)
        }
    }

    suspend fun addToNeverAgainLocal(restaurantId: String) {
        if (restaurantRepository.isFavoritedInLocal(restaurantId)) {
            restaurantRepository.removeFromFavoritesLocal(restaurantId)  // Can't be both
        }
        restaurantRepository.addToNeverAgainLocal(restaurantId)
    }


}