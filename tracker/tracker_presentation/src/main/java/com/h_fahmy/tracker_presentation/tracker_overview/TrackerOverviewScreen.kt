package com.h_fahmy.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.util.BaseLightPreview
import com.h_fahmy.tracker_domain.model.TrackedFood
import com.h_fahmy.tracker_presentation.tracker_overview.components.AddButton
import com.h_fahmy.tracker_presentation.tracker_overview.components.DaySelector
import com.h_fahmy.tracker_presentation.tracker_overview.components.ExpandableMeal
import com.h_fahmy.tracker_presentation.tracker_overview.components.NutrientHeader
import com.h_fahmy.tracker_presentation.tracker_overview.components.TrackedFoodItem

@Composable
fun TrackerOverviewScreen(
    onNavigateToSearch: (String, Int, Int, Int) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    val context = LocalContext.current

    TrackerOverviewContent(
        onPreviousDayClick = { viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClicked) },
        onNextDayClick = { viewModel.onEvent(TrackerOverviewEvent.OnNextDayClicked) },
        onToggleMealClicked = { viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClicked(it)) },
        onDeleteFoodClicked = { viewModel.onEvent(TrackerOverviewEvent.DeleteTrackedFood(it)) },
        onAddMealClicked = {
            onNavigateToSearch(
                it.name.asString(context),
                state.date.dayOfMonth,
                state.date.monthValue,
                state.date.year
            )
        },
        state = state,
    )
}

@Composable
private fun TrackerOverviewContent(
    onPreviousDayClick: () -> Unit,
    onNextDayClick: () -> Unit,
    onToggleMealClicked: (Meal) -> Unit,
    onDeleteFoodClicked: (TrackedFood) -> Unit,
    onAddMealClicked: (Meal) -> Unit,
    state: TrackerOverviewState,
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientHeader(state = state)

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            DaySelector(
                date = state.date,
                onPreviousDayClick = onPreviousDayClick,
                onNextDayClick = onNextDayClick,
                modifier = Modifier.padding(horizontal = spacing.spaceMedium)
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }

        itemsIndexed(state.meals, key = { _, meal -> meal.mealType }) { index, meal ->
            ExpandableMeal(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceSmall),
                meal = meal,
                onToggleClick = { onToggleMealClicked(meal) }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceSmall)
                ) {
                    state.trackedFoods.forEach { trackedFood ->
                        TrackedFoodItem(
                            trackedFood = trackedFood,
                            onDeleteClicked = { onDeleteFoodClicked(trackedFood) }
                        )

                        Spacer(modifier = Modifier.height(spacing.spaceMedium))
                    }

                    AddButton(
                        text = stringResource(
                            id = R.string.add_meal,
                            meal.name.asString(context)
                        ),
                        onClick = { onAddMealClicked(meal) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(spacing.spaceMedium))
                }
            }

            if (index != state.meals.lastIndex) {
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackerOverviewContentPreview() {
    BaseLightPreview {
        TrackerOverviewContent(
            onPreviousDayClick = { },
            onNextDayClick = { },
            onToggleMealClicked = {},
            onDeleteFoodClicked = {},
            onAddMealClicked = {},
            state = TrackerOverviewState(
                totalCalories = 1000,
                totalCarbs = 400,
                totalFat = 900,
                totalProtein = 700,
                carbsGoal = 500,
                fatGoal = 1000,
                proteinGoal = 1000,
                caloriesGoal = 2500,
            )
        )
    }
}