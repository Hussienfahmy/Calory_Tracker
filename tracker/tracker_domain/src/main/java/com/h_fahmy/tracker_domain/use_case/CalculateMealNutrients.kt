package com.h_fahmy.tracker_domain.use_case

import com.h_fahmy.core.domain.model.ActivityLevel
import com.h_fahmy.core.domain.model.Gender
import com.h_fahmy.core.domain.model.GoalType
import com.h_fahmy.core.domain.model.UserInfo
import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.tracker_domain.model.MealType
import com.h_fahmy.tracker_domain.model.TrackedFood
import kotlin.math.roundToInt

class CalculateMealNutrients(
    private val preferences: Preferences,
) {
    suspend operator fun invoke(trackedFood: List<TrackedFood>): Result {
        val allNutrients = trackedFood.groupBy { it.mealType }.mapValues { entry ->
            val mealType = entry.key
            val food = entry.value

            MealNutrients(
                carbs = food.sumOf { it.carbs },
                fat = food.sumOf { it.fat },
                protein = food.sumOf { it.protein },
                calories = food.sumOf { it.calories },
                type = mealType,
            )
        }

        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalFat = allNutrients.values.sumOf { it.fat }
        val totalProtein = allNutrients.values.sumOf { it.protein }
        val totalCalories = allNutrients.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()

        val caloriesGoal = dailyCalorieRequirement(userInfo)
        val carbsGoal = (caloriesGoal * userInfo.carbRatio / 4f).roundToInt()
        val fatGoal = (caloriesGoal * userInfo.fatRatio / 9f).roundToInt()
        val proteinGoal = (caloriesGoal * userInfo.proteinRatio / 4f).roundToInt()

        return Result(
            caloriesGoal = caloriesGoal,
            carbsGoal = carbsGoal,
            fatGoal = fatGoal,
            proteinGoal = proteinGoal,
            totalCalories = totalCalories,
            totalCarbs = totalCarbs,
            totalFat = totalFat,
            totalProtein = totalProtein,
            mealNutrients = allNutrients,
        )
    }

    // basal metabolism rate
    private fun bmr(userInfo: UserInfo): Int {
        return when (userInfo.gender) {
            Gender.Male -> (66.47f + 13.75f * userInfo.weight + 5.003f * userInfo.height - 6.755f * userInfo.age).roundToInt()
            Gender.Female -> (655.1f + 9.563f * userInfo.weight + 1.85f * userInfo.height - 4.676f * userInfo.age).roundToInt()
        }
    }

    private fun dailyCalorieRequirement(userInfo: UserInfo): Int {
        val activityFactor = when (userInfo.activityLevel) {
            ActivityLevel.Low -> 1.2f
            ActivityLevel.Medium -> 1.3f
            ActivityLevel.High -> 1.4f
        }

        val calorieExtra = when (userInfo.goalType) {
            GoalType.LoseWeight -> -500
            GoalType.KeepWeight -> 0
            GoalType.GainWeight -> 500
        }

        return (bmr(userInfo) * activityFactor + calorieExtra).roundToInt()
    }

    data class MealNutrients(
        val calories: Int,
        val carbs: Int,
        val fat: Int,
        val protein: Int,
        val type: MealType,
    )

    data class Result(
        val carbsGoal: Int,
        val fatGoal: Int,
        val proteinGoal: Int,
        val caloriesGoal: Int,
        val totalCarbs: Int,
        val totalFat: Int,
        val totalProtein: Int,
        val totalCalories: Int,
        val mealNutrients: Map<MealType, MealNutrients>,
    )
}