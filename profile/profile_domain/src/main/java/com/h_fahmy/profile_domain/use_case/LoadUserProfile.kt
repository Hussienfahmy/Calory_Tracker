package com.h_fahmy.profile_domain.use_case

import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.profile_domain.model.UserProfile
import kotlinx.coroutines.flow.map

class LoadUserProfile(
    private val preferences: Preferences,
) {
    operator fun invoke() = preferences.observeUserInfo().map { UserProfile(it) }
}