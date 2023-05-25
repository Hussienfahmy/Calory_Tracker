package com.h_fahmy.onboarding_presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.h_fahmy.core.domain.model.Gender
import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.core_ui.util.UiEvent
import com.h_fahmy.core_ui.viewmodel.UiEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val preferences: Preferences,
) : UiEventViewModel() {

    var selectedGender by mutableStateOf(Gender.Male)
        private set

    fun onGenderClick(gender: Gender) {
        selectedGender = gender
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveGender(selectedGender)
            _uiEvent.send(UiEvent.Success)
        }
    }
}