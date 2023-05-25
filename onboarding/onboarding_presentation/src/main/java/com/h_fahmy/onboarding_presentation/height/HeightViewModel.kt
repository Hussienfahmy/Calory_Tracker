package com.h_fahmy.onboarding_presentation.height

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
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
) : UiEventViewModel() {

    var height by mutableStateOf("170")
        private set

    fun onHeightEnter(height: String) {
        if (height.length <= 3) {
            this.height = filterOutDigits(height)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val heightNumber = height.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        UiText.StringResource(
                            resId = R.string.error_height_cant_be_empty
                        )
                    )
                )
                return@launch
            }
            preferences.saveHeight(heightNumber)
            _uiEvent.send(UiEvent.Success)
        }
    }
}