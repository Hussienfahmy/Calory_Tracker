package com.h_fahmy.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.core.domain.use_case.FilterOutDigits
import com.h_fahmy.core.navigation.Route
import com.h_fahmy.core_ui.util.UiEvent
import com.h_fahmy.core_ui.viewmodel.UiEventViewModel
import com.h_fahmy.onboarding_domain.use_case.ValidateNutrients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
) : UiEventViewModel() {

    var state by mutableStateOf(NutrientGoalState())
        private set

    fun onEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.OnCarbsRationEntered -> {
                state = state.copy(carbsRatio = filterOutDigits(event.ratio))
            }

            is NutrientGoalEvent.OnFatRationEntered -> {
                state = state.copy(fatRatio = filterOutDigits(event.ratio))
            }

            is NutrientGoalEvent.OnProteinRationEntered -> {
                state = state.copy(proteinRatio = filterOutDigits(event.ratio))
            }

            is NutrientGoalEvent.OnNextClicked -> {
                val result = validateNutrients(
                    carbsRatio = state.carbsRatio,
                    proteinRatio = state.proteinRatio,
                    fatRatio = state.fatRatio
                )

                viewModelScope.launch {
                    when (result) {
                        is ValidateNutrients.Result.Success -> {
                            preferences.saveCarbRatio(result.carbsRatio)
                            preferences.saveProteinRatio(result.proteinRatio)
                            preferences.saveFatRatio(result.fatRatio)
                            _uiEvent.send(
                                UiEvent.Navigate(Route.TRACKER_OVERVIEW)
                            )
                        }

                        is ValidateNutrients.Result.Error -> {
                            _uiEvent.send(
                                UiEvent.ShowSnackBar(message = result.message)
                            )
                        }
                    }
                }
            }
        }
    }
}