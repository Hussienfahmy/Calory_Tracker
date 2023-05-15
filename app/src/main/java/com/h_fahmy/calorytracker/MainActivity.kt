package com.h_fahmy.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.h_fahmy.calorytracker.navigation.navigate
import com.h_fahmy.core.navigation.Route
import com.h_fahmy.core_ui.theme.CaloryTrackerTheme
import com.h_fahmy.onboarding_presentation.activity.ActivityScreen
import com.h_fahmy.onboarding_presentation.age.AgeScreen
import com.h_fahmy.onboarding_presentation.gender.GenderScreen
import com.h_fahmy.onboarding_presentation.goal.GoalScreen
import com.h_fahmy.onboarding_presentation.height.HeightScreen
import com.h_fahmy.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.h_fahmy.onboarding_presentation.weight.WeightScreen
import com.h_fahmy.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme(darkTheme = false) {
                val navController = rememberNavController()
                val snackBarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
                ) {
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        navController = navController, startDestination = Route.WELCOME
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(
                                onNavigate = navController::navigate
                            )
                        }

                        composable(Route.AGE) {
                            AgeScreen(
                                snackBarHostState = snackBarHostState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                onNavigate = navController::navigate,
                                snackBarHostState = snackBarHostState
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                onNavigate = navController::navigate,
                                snackBarHostState = snackBarHostState
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                onNavigate = navController::navigate,
                                snackBarHostState = snackBarHostState
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.GOAL) {
                            GoalScreen(onNavigate = navController::navigate)
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
}