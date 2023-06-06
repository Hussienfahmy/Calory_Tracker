package com.h_fahmy.profile_presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.core.domain.use_case.FilterOutDigits
import com.h_fahmy.core_ui.domain.use_case.ValidateNutrients
import com.h_fahmy.profile_domain.use_case.LoadUserProfile
import com.h_fahmy.profile_presentation.ProfileEvent
import com.h_fahmy.profile_presentation.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    loadUserProfile: LoadUserProfile,
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients,
) : ViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

    init {
        loadUserProfile().onEach {
            state = state.copy(userProfile = it)
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: ProfileEvent) {
        viewModelScope.launch {
            when (event) {
                is ProfileEvent.OnActivityLevelChanged -> preferences.saveActivityLevel(event.activityLevel)
                is ProfileEvent.OnGenderChanged -> preferences.saveGender(event.gender)
                is ProfileEvent.OnGoalTypeChanged -> preferences.saveGoalType(event.goalType)
                is ProfileEvent.OnAgeChanged -> {
                    filterOutDigits(event.age).toIntOrNull().let {
                        when (it) {
                            null -> {}
                            else -> preferences.saveAge(it)
                        }
                    }
                }

                is ProfileEvent.OnHeightChanged -> {
                    filterOutDigits(event.height).toIntOrNull().let {
                        when (it) {
                            null -> {}
                            else -> preferences.saveHeight(it)
                        }
                    }
                }

                is ProfileEvent.OnWeightChanged -> {
                    filterOutDigits(event.weight).toFloatOrNull().let {
                        when (it) {
                            null -> {}
                            else -> preferences.saveWeight(it)
                        }
                    }
                }

                is ProfileEvent.OnNutrientsGoalChanged -> {
                    // display the number on the screen but don't save it until validated
                    state = state.copy(
                        userProfile = state.userProfile?.copy(
                            carbRatio = filterOutDigits(event.carbRatio).toIntOrNull() ?: 0,
                            proteinRatio = filterOutDigits(event.proteinRatio).toIntOrNull() ?: 0,
                            fatRatio = filterOutDigits(event.fatRatio).toIntOrNull() ?: 0,
                        )
                    )

                    val result = validateNutrients(
                        carbsRatio = event.carbRatio,
                        proteinRatio = event.proteinRatio,
                        fatRatio = event.fatRatio
                    )

                    when (result) {
                        is ValidateNutrients.Result.Error -> {
                            state = state.copy(
                                invalidCarbRatio = true,
                                invalidProteinRatio = true,
                                invalidFatRatio = true,
                            )
                        }

                        is ValidateNutrients.Result.Success -> {
                            state = state.copy(
                                invalidCarbRatio = false,
                                invalidProteinRatio = false,
                                invalidFatRatio = false,
                            )
                            preferences.saveCarbRatio(result.carbsRatio)
                            preferences.saveProteinRatio(result.proteinRatio)
                            preferences.saveFatRatio(result.fatRatio)
                        }
                    }
                }
            }
        }
    }
}