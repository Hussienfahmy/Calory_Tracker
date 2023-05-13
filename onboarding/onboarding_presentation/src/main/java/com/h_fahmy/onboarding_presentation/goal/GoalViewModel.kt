package com.h_fahmy.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.h_fahmy.core.domain.model.GoalType
import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.core.navigation.Route
import com.h_fahmy.core_ui.util.UiEvent
import com.h_fahmy.core_ui.viewmodel.UiEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val preferences: Preferences,
) : UiEventViewModel() {

    var selectedGoal by mutableStateOf(GoalType.KeepWeight)
        private set

    fun onGoalTypeSelected(goalType: GoalType) {
        selectedGoal = goalType
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveGoalType(selectedGoal)
            _uiEvent.send(UiEvent.Navigate(Route.NUTRIENT_GOAL))
        }
    }
}