package com.h_fahmy.calorytracker.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

fun NavHostController.navigateWithClearBackStack(route: String) = navigate(route) {
    popUpTo(graph.findStartDestination().id) { saveState = true }
    restoreState = true
}