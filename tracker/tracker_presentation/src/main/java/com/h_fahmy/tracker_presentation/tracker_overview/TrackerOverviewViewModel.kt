package com.h_fahmy.tracker_presentation.tracker_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.core.navigation.Route
import com.h_fahmy.core_ui.util.UiEvent
import com.h_fahmy.core_ui.viewmodel.UiEventViewModel
import com.h_fahmy.tracker_domain.use_case.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    preferences: Preferences,
    private val trackerUseCases: TrackerUseCases,
) : UiEventViewModel() {

    var state by mutableStateOf(TrackerOverviewState())
        private set

    private var getFoodForDateJob: Job? = null

    init {
        refreshFood()
        viewModelScope.launch {
            preferences.saveShouldShowOnBoarding(false)
        }
    }

    fun onEvent(event: TrackerOverviewEvent) {
        when (event) {
            is TrackerOverviewEvent.OnAddFoodClicked -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Navigate(
                            Route.SEARCH
                                    + "/${event.meal.mealType.displayName}"
                                    + "/${state.date.dayOfMonth}"
                                    + "/${state.date.monthValue}"
                                    + "/${state.date.year}"
                        )
                    )
                }
            }

            is TrackerOverviewEvent.DeleteTrackedFood -> {
                viewModelScope.launch {
                    trackerUseCases.deleteTrackedFood(event.trackedFood)
                    refreshFood()
                }
            }

            is TrackerOverviewEvent.OnNextDayClicked -> {
                state = state.copy(date = state.date.plusDays(1))
                refreshFood()
            }

            is TrackerOverviewEvent.OnPreviousDayClicked -> {
                state = state.copy(date = state.date.minusDays(1))
                refreshFood()
            }

            is TrackerOverviewEvent.OnToggleMealClicked -> {
                state = state.copy(
                    meals = state.meals.map {
                        if (it.name == event.meal.name) {
                            it.copy(isExpanded = !it.isExpanded)
                        } else {
                            it
                        }
                    }
                )
            }
        }
    }

    private fun refreshFood() {
        getFoodForDateJob?.cancel()
        getFoodForDateJob = trackerUseCases
            .getFoodsByDate(state.date)
            .onEach { food ->
                val nutrientsResult = trackerUseCases.calculateMealNutrients(food)
                state = state.copy(
                    totalCarbs = nutrientsResult.totalCarbs,
                    totalProtein = nutrientsResult.totalProtein,
                    totalFat = nutrientsResult.totalFat,
                    totalCalories = nutrientsResult.totalCalories,
                    carbsGoal = nutrientsResult.carbsGoal,
                    proteinGoal = nutrientsResult.proteinGoal,
                    fatGoal = nutrientsResult.fatGoal,
                    caloriesGoal = nutrientsResult.caloriesGoal,
                    trackedFoods = food,
                    meals = state.meals.map {
                        val nutrientsPerMeal = nutrientsResult.mealNutrients[it.mealType]
                            ?: return@map it.copy(
                                carbsAmount = 0,
                                proteinAmount = 0,
                                fatAmount = 0,
                                caloriesAmount = 0,
                            )

                        it.copy(
                            carbsAmount = nutrientsPerMeal.carbs,
                            proteinAmount = nutrientsPerMeal.protein,
                            fatAmount = nutrientsPerMeal.fat,
                            caloriesAmount = nutrientsPerMeal.calories,
                        )
                    }
                )
            }.launchIn(viewModelScope)
    }
}