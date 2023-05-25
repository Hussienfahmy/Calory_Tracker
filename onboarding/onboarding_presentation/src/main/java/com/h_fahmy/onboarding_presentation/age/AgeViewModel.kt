package com.h_fahmy.onboarding_presentation.age

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.core.domain.use_case.FilterOutDigits
import com.h_fahmy.core_ui.util.UiEvent
import com.h_fahmy.core_ui.util.UiText
import com.h_fahmy.core_ui.viewmodel.UiEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
) : UiEventViewModel() {

    var age by mutableStateOf("24")
        private set

    fun onAgeEnter(age: String) {
        if (age.length <= 3) {
            this.age = filterOutDigits(age)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val ageNumber = age.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        UiText.StringResource(
                            resId = R.string.error_age_cant_be_empty
                        )
                    )
                )
                return@launch
            }
            preferences.saveAge(ageNumber)
            _uiEvent.send(UiEvent.Success)
        }
    }
}