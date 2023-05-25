package com.h_fahmy.onboarding_presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.core_ui.util.UiEvent
import com.h_fahmy.core_ui.util.UiText
import com.h_fahmy.core_ui.viewmodel.UiEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: Preferences,
) : UiEventViewModel() {

    var weight by mutableStateOf("80.0")
        private set

    fun onWeightEnter(weight: String) {
        if (weight.length <= 5) {
            this.weight = weight
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val weightNumber = weight.toFloatOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        UiText.StringResource(
                            resId = R.string.error_weight_cant_be_empty
                        )
                    )
                )
                return@launch
            }
            preferences.saveWeight(weightNumber)
            _uiEvent.send(UiEvent.Success)
        }
    }
}