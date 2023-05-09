package com.h_fahmy.calorytracker.navigation

import androidx.navigation.NavHostController
import com.h_fahmy.core_ui.util.UiEvent

fun NavHostController.navigate(event: UiEvent) {
    when (event) {
        is UiEvent.Navigate -> navigate(event.route)
        UiEvent.NavigateUp -> navigateUp()
    }
}