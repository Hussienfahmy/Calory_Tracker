package com.h_fahmy.onboarding_presentation.nutrient_goal

sealed class NutrientGoalEvent {
    data class OnCarbsRationEntered(val ratio: String) : NutrientGoalEvent()

    data class OnProteinRationEntered(val ratio: String) : NutrientGoalEvent()

    data class OnFatRationEntered(val ratio: String) : NutrientGoalEvent()

    object OnNextClicked : NutrientGoalEvent()
}