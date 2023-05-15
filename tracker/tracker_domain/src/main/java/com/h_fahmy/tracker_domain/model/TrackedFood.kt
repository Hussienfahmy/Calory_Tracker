package com.h_fahmy.tracker_domain.model

import java.time.LocalDate

/**
 * Represents a food that tracked by the user.
 */
data class TrackedFood(
    val name: String,
    val imageUrl: String?,
    val carbs: Int,
    val fat: Int,
    val protein: Int,
    val mealType: MealType,
    val amount: Int,
    val date: LocalDate,
    val calories: Int,
    val id: Int = 0,
)
