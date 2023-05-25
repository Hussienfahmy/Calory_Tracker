package com.h_fahmy.onboarding_presentation.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.h_fahmy.core.domain.model.ActivityLevel
import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.core_ui.util.UiEvent
import com.h_fahmy.core_ui.viewmodel.UiEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val preferences: Preferences,
) : UiEventViewModel() {

    var selectedActivityLevel by mutableStateOf(ActivityLevel.Medium)
        private set

    fun onActivityLevelSelected(activityLevel: ActivityLevel) {
        selectedActivityLevel = activityLevel
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveActivityLevel(selectedActivityLevel)
            _uiEvent.send(UiEvent.Success)
        }
    }
}