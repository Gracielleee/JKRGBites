package com.jrkg.jrkgbites.utils

import android.location.Location

/**
 * Calculates the distance in meters between two geographical points.
 *
 * This function is a helper that wraps Android's built-in distance calculation.
 *
 * @param startLat Latitude of the starting point.
 * @param startLon Longitude of the starting point.
 * @param endLat Latitude of the ending point.
 * @param endLon Longitude of the ending point.
 * @return The approximate distance in meters between the two points.
 */
fun calculateDistance(startLat: Double, startLon: Double, endLat: Double, endLon: Double): Float {
    val results = FloatArray(1)
    Location.distanceBetween(startLat, startLon, endLat, endLon, results)
    return results[0]
}
