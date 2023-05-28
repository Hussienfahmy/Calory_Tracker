package com.h_fahmy.core.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.h_fahmy.core.domain.model.ActivityLevel
import com.h_fahmy.core.domain.model.Gender
import com.h_fahmy.core.domain.model.GoalType
import com.h_fahmy.core.domain.model.UserInfo
import com.h_fahmy.core.domain.preferences.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

/**
 * UserInfoDataStore is a class that implements the [Preferences] interface
 * and is responsible for saving and loading user information data.
 * The user information data is stored in a DataStore<[UserInfo]> object.
 */
class UserInfoDataStore(
    private val dataStore: DataStore<UserInfo>
) : Preferences {

    override suspend fun saveGender(gender: Gender) {
        dataStore.updateData {
            it.copy(gender = gender)
        }
    }

    override suspend fun saveAge(age: Int) {
        dataStore.updateData {
            it.copy(age = age)
        }
    }

    override suspend fun saveHeight(height: Int) {
        dataStore.updateData {
            it.copy(height = height)
        }
    }

    override suspend fun saveWeight(weight: Float) {
        dataStore.updateData {
            it.copy(weight = weight)
        }
    }

    override suspend fun saveActivityLevel(activityLevel: ActivityLevel) {
        dataStore.updateData {
            it.copy(activityLevel = activityLevel)
        }
    }

    override suspend fun saveGoalType(goalType: GoalType) {
        dataStore.updateData {
            it.copy(goalType = goalType)
        }
    }

    override suspend fun saveCarbRatio(ratio: Float) {
        dataStore.updateData {
            it.copy(carbRatio = ratio)
        }
    }

    override suspend fun saveProteinRatio(ratio: Float) {
        dataStore.updateData {
            it.copy(proteinRatio = ratio)
        }
    }

    override suspend fun saveFatRatio(ratio: Float) {
        dataStore.updateData {
            it.copy(fatRatio = ratio)
        }
    }

    override suspend fun loadUserInfo(): UserInfo {
        return dataStore.data.first()
    }

    override fun observeUserInfo(): Flow<UserInfo> {
        return dataStore.data
    }

    override suspend fun saveShouldShowOnBoarding(shouldShowOnBoarding: Boolean) {
        dataStore.updateData {
            it.copy(shouldShowOnBoarding = shouldShowOnBoarding)
        }
    }

    override suspend fun loadShouldShowOnBoarding(): Boolean {
        return dataStore.data.first().shouldShowOnBoarding
    }

    companion object {
        private val Context.dataStore by dataStore("user_info", UserInfoSerializer)

        fun create(context: Context): UserInfoDataStore {
            val dataStore = context.dataStore
            return UserInfoDataStore(dataStore)
        }
    }
}