package com.h_fahmy.profile_presentation

import com.h_fahmy.core.domain.model.ActivityLevel
import com.h_fahmy.core.domain.model.Gender
import com.h_fahmy.core.domain.model.GoalType

sealed class ProfileEvent {
    data class OnGenderChanged(val gender: Gender) : ProfileEvent()
    data class OnAgeChanged(val age: String) : ProfileEvent()
    data class OnHeightChanged(val height: String) : ProfileEvent()
    data class OnWeightChanged(val weight: String) : ProfileEvent()
    data class OnActivityLevelChanged(val activityLevel: ActivityLevel) : ProfileEvent()
    data class OnGoalTypeChanged(val goalType: GoalType) : ProfileEvent()
    data class OnCarbRatioChanged(val carbRatio: String) : ProfileEvent()
    data class OnProteinRatioChanged(val proteinRatio: String) : ProfileEvent()
    data class OnFatRatioChanged(val fatRatio: String) : ProfileEvent()
}