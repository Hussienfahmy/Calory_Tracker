package com.h_fahmy.core.domain.preferences

import com.h_fahmy.core.domain.model.ActivityLevel
import com.h_fahmy.core.domain.model.Gender
import com.h_fahmy.core.domain.model.GoalType
import com.h_fahmy.core.domain.model.UserInfo

/**
 * Interface that defines methods for saving and loading user information data.
 */
interface Preferences {

    suspend fun saveGender(gender: Gender)

    suspend fun saveAge(age: Int)

    suspend fun saveHeight(height: Int)

    suspend fun saveWeight(weight: Int)

    suspend fun saveActivityLevel(activityLevel: ActivityLevel)

    suspend fun saveGoalType(goalType: GoalType)

    suspend fun saveCarbRatio(ratio: Float)

    suspend fun saveProteinRatio(ratio: Float)

    suspend fun saveFatRatio(ratio: Float)

    suspend fun loadUserInfo(): UserInfo
}