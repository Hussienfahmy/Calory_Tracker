package com.h_fahmy.tracker_domain.use_case

import com.h_fahmy.tracker_domain.model.MealType
import com.h_fahmy.tracker_domain.model.TrackableFood
import com.h_fahmy.tracker_domain.model.TrackedFood
import com.h_fahmy.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class TrackFood(
    private val repository: TrackerRepository,
) {

    suspend operator fun invoke(
        trackableFood: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate,
    ) {
        repository.insertTrackedFood(
            TrackedFood(
                name = trackableFood.name,
                imageUrl = trackableFood.imageUrl,
                carbs = ((trackableFood.carbsPer100g / 100f) * amount).roundToInt(),
                fat = ((trackableFood.fatPer100g / 100f) * amount).roundToInt(),
                protein = ((trackableFood.proteinPer100g / 100f) * amount).roundToInt(),
                mealType = mealType,
                amount = amount,
                date = date,
                calories = ((trackableFood.caloriesPer100g / 100f) * amount).roundToInt(),
            )
        )
    }
}