package com.h_fahmy.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val gender: Gender = Gender.Male,
    val age: Int = 24,
    val height: Int = 170,
    val weight: Float = 80.0f,
    val activityLevel: ActivityLevel = ActivityLevel.Medium,
    val goalType: GoalType = GoalType.LoseWeight,
    val carbRatio: Float = 0.5f,
    val proteinRatio: Float = 0.3f,
    val fatRatio: Float = 0.2f,
    val shouldShowOnBoarding: Boolean = true
)
