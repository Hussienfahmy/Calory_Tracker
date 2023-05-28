package com.h_fahmy.profile_domain.model

import com.h_fahmy.core.domain.model.ActivityLevel
import com.h_fahmy.core.domain.model.Gender
import com.h_fahmy.core.domain.model.GoalType
import com.h_fahmy.core.domain.model.UserInfo

data class UserProfile(
    val age: Int,
    val height: Int,
    val weight: Float,
    val gender: Gender,
    val activityLevel: ActivityLevel,
    val goalType: GoalType,
    val carbRatio: Int,
    val proteinRatio: Int,
    val fatRatio: Int,
) {
    constructor(userInfo: UserInfo) : this(
        age = userInfo.age,
        height = userInfo.height,
        weight = userInfo.weight,
        gender = userInfo.gender,
        activityLevel = userInfo.activityLevel,
        goalType = userInfo.goalType,
        carbRatio = (userInfo.carbRatio * 100).toInt(),
        proteinRatio = (userInfo.proteinRatio * 100).toInt(),
        fatRatio = (userInfo.fatRatio * 100).toInt()
    )
}