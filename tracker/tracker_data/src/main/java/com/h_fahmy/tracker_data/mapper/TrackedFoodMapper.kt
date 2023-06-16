package com.h_fahmy.tracker_data.mapper

import com.h_fahmy.tracker_data.local.entity.TrackedFoodEntity
import com.h_fahmy.tracker_domain.model.MealType
import com.h_fahmy.tracker_domain.model.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    return TrackedFood(
        name = name,
        imageUrl = imageUrl,
        carbs = carbs,
        fat = fat,
        protein = protein,
        mealType = MealType.valueOf(type.name),
        amount = amount,
        date = LocalDate.of(date.year, date.month, date.day),
        calories = calories,
        id = id,
    )
}


fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        name = name,
        imageUrl = imageUrl,
        carbs = carbs,
        fat = fat,
        protein = protein,
        type = TrackedFoodEntity.Type.valueOf(mealType.name),
        amount = 100,
        date = TrackedFoodEntity.Date(
            day = date.dayOfMonth,
            month = date.monthValue,
            year = date.year
        ),
        calories = calories,
        id = id,
    )
}