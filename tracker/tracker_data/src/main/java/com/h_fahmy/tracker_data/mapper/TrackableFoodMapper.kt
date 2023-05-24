package com.h_fahmy.tracker_data.mapper

import com.h_fahmy.tracker_data.remote.dto.Product
import com.h_fahmy.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt

fun Product.toTrackableFood(): TrackableFood? {
    val caloriesPer100g = nutriments.energyKcal100g.roundToInt()
    val carbsPer100g = nutriments.carbohydrates100g.roundToInt()
    val fatPer100g = nutriments.fat100g.roundToInt()
    val proteinPer100g = nutriments.proteins100g.roundToInt()

    return TrackableFood(
        name = productName ?: return null,
        imageUrl = imageFrontThumbUrl,
        caloriesPer100g = caloriesPer100g,
        carbsPer100g = carbsPer100g,
        fatPer100g = fatPer100g,
        proteinPer100g = proteinPer100g
    )
}