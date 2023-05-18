package com.h_fahmy.tracker_presentation.tracker_overview

import androidx.annotation.DrawableRes
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core_ui.util.UiText
import com.h_fahmy.tracker_domain.model.MealType

data class Meal(
    val name: UiText,
    @DrawableRes val drawableRes: Int,
    val mealType: MealType,
    val carbsAmount: Int = 0,
    val proteinAmount: Int = 0,
    val fatAmount: Int = 0,
    val caloriesAmount: Int = 0,
    val isExpanded: Boolean = false,
)

val defaultMeals = listOf(
    Meal(
        name = UiText.StringResource(R.string.breakfast),
        drawableRes = R.drawable.ic_breakfast,
        mealType = MealType.BREAKFAST
    ),
    Meal(
        name = UiText.StringResource(R.string.lunch),
        drawableRes = R.drawable.ic_lunch,
        mealType = MealType.LUNCH
    ),
    Meal(
        name = UiText.StringResource(R.string.dinner),
        drawableRes = R.drawable.ic_dinner,
        mealType = MealType.DINNER
    ),
    Meal(
        name = UiText.StringResource(R.string.snacks),
        drawableRes = R.drawable.ic_snack,
        mealType = MealType.SNACK
    ),
)