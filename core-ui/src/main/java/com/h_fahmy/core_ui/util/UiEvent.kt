package com.h_fahmy.core_ui.util

sealed class UiEvent {
    /**
     * Means that the purpose of the screen is done and we can navigate to the next screen
     */
    object Success : UiEvent()

    data class ShowSnackBar(val message: UiText) : UiEvent()
}