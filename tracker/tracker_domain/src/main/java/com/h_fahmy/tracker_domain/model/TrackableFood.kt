package com.h_fahmy.tracker_domain.model

/**
 * A food that can be tracked, represents the food fetched from the API with the search query.
 */
data class TrackableFood(
    val name: String,
    val imageUrl: String?,
    val caloriesPer100g: Int,
    val carbsPer100g: Int,
    val fatPer100g: Int,
    val proteinPer100g: Int,
)
