package com.h_fahmy.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.h_fahmy.calorytracker.navigation.AppBottomNavigation
import com.h_fahmy.calorytracker.navigation.Route
import com.h_fahmy.calorytracker.navigation.navigateWithClearBackStack
import com.h_fahmy.core_ui.theme.CaloryTrackerTheme
import com.h_fahmy.onboarding_presentation.activity.ActivityScreen
import com.h_fahmy.onboarding_presentation.age.AgeScreen
import com.h_fahmy.onboarding_presentation.gender.GenderScreen
import com.h_fahmy.onboarding_presentation.goal.GoalScreen
import com.h_fahmy.onboarding_presentation.height.HeightScreen
import com.h_fahmy.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.h_fahmy.onboarding_presentation.weight.WeightScreen
import com.h_fahmy.onboarding_presentation.welcome.WelcomeScreen
import com.h_fahmy.profile_presentation.profile.ProfileScreen
import com.h_fahmy.tracker_presentation.search.SearchScreen
import com.h_fahmy.tracker_presentation.tracker_overview.TrackerOverviewScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shouldShowOnBoarding = runBlocking { viewModel.shouldShowOnBoarding() }

        setContent {
            CaloryTrackerTheme(darkTheme = false) {
                val navController = rememberNavController()
                val snackBarHostState = remember { SnackbarHostState() }
                val currentDestination by navController.currentBackStackEntryAsState()
                val currentRoute = currentDestination?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
                    bottomBar = {
                        if (currentRoute != null && when (currentRoute) {
                                Route.TRACKER_OVERVIEW, Route.PROFILE -> true
                                else -> false
                            }
                        ) {
                            AppBottomNavigation(
                                currentDestination = currentRoute,
                                onTrackerOverviewClick = {
                                    navController.navigateWithClearBackStack(Route.TRACKER_OVERVIEW)
                                },
                                onProfileClick = { navController.navigateWithClearBackStack(Route.PROFILE) }
                            )
                        }
                    }
                ) { paddingValues ->
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        navController = navController,
                        startDestination = if (shouldShowOnBoarding) Route.WELCOME else Route.TRACKER_OVERVIEW
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(
                                OnNextClick = {
                                    navController.navigate(Route.AGE)
                                }
                            )
                        }

                        composable(Route.AGE) {
                            AgeScreen(
                                snackBarHostState = snackBarHostState,
                                OnNextClick = {
                                    navController.navigate(Route.GENDER)
                                }
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(
                                OnNextClick = {
                                    navController.navigate(Route.HEIGHT)
                                }
                            )
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                OnNextClick = {
                                    navController.navigate(Route.WEIGHT)
                                },
                                snackBarHostState = snackBarHostState
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                OnNextClick = {
                                    navController.navigate(Route.NUTRIENT_GOAL)
                                },
                                snackBarHostState = snackBarHostState
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                OnNextClick = {
                                    navController.navigate(Route.ACTIVITY)
                                },
                                snackBarHostState = snackBarHostState
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(OnNextClick = {
                                navController.navigate(Route.GOAL)
                            })
                        }
                        composable(Route.GOAL) {
                            GoalScreen(OnNextClick = {
                                navController.navigate(Route.TRACKER_OVERVIEW)
                            })
                        }
                        composable(Route.TRACKER_OVERVIEW) {
                            TrackerOverviewScreen(onNavigateToSearch = { mealName, dayOfMonth, month, year ->
                                navController.navigate(
                                    Route.SEARCH + "/${mealName}/${dayOfMonth}/${month}/${year}"
                                )
                            })
                        }
                        composable(
                            route = Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument("mealName") { type = NavType.StringType },
                                navArgument("dayOfMonth") { type = NavType.IntType },
                                navArgument("month") { type = NavType.IntType },
                                navArgument("year") { type = NavType.IntType }
                            )
                        ) {
                            val mealName = it.arguments?.getString("mealName")!!
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                            val month = it.arguments?.getInt("month")!!
                            val year = it.arguments?.getInt("year")!!

                            SearchScreen(
                                onFoodTracked = { navController.navigateUp() },
                                mealName = mealName,
                                dayOfMonth = dayOfMonth,
                                month = month,
                                year = year,
                                snackBarHostState = snackBarHostState
                            )
                        }

                        composable(Route.PROFILE) {
                            ProfileScreen()
                        }
                    }
                }
            }
        }
    }
}