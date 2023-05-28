package com.h_fahmy.calorytracker

import androidx.lifecycle.ViewModel
import com.h_fahmy.core.domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    suspend fun shouldShowOnBoarding(): Boolean {
        return preferences.loadShouldShowOnBoarding()
    }
}