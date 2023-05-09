package com.h_fahmy.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.h_fahmy.calorytracker.navigation.navigate
import com.h_fahmy.core.navigation.Route
import com.h_fahmy.core_ui.theme.CaloryTrackerTheme
import com.h_fahmy.onboarding_presentation.gender.GenderScreen
import com.h_fahmy.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme(darkTheme = false) {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Route.WELCOME) {
                    composable(Route.WELCOME) {
                        WelcomeScreen(
                            onNavigate = navController::navigate
                        )
                    }

                    composable(Route.AGE) {

                    }
                    composable(Route.GENDER) {
                        GenderScreen(
                            onNavigate = navController::navigate
                        )
                    }
                    composable(Route.HEIGHT) {

                    }
                    composable(Route.WEIGHT) {

                    }
                    composable(Route.NUTRIENT_GOAL) {

                    }
                    composable(Route.ACTIVITY) {

                    }
                    composable(Route.GOAL) {

                    }
                    composable(Route.TRACKER_OVERVIEW) {

                    }
                    composable(Route.SEARCH) {

                    }
                }
            }
        }
    }
}