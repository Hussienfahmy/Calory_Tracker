package com.h_fahmy.tracker_domain.model

import androidx.annotation.StringRes
import com.h_fahmy.calorytracker.core.R

enum class MealType(@StringRes val displayName: Int) {
    BREAKFAST(R.string.breakfast),
    LUNCH(R.string.lunch),
    DINNER(R.string.dinner),
    SNACK(R.string.snacks)
}
