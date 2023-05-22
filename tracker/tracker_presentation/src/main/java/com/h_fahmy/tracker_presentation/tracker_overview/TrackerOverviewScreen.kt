package com.h_fahmy.tracker_presentation.tracker_overview

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.util.UiEvent
import com.h_fahmy.tracker_presentation.tracker_overview.components.DaySelector
import com.h_fahmy.tracker_presentation.tracker_overview.components.ExpandableMeal
import com.h_fahmy.tracker_presentation.tracker_overview.components.NutrientHeader

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
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
                onPreviousDayClick = { viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClicked) },
                onNextDayClick = { viewModel.onEvent(TrackerOverviewEvent.OnNextDayClicked) },
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
                onToggleClick = { viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClicked(meal)) }
            ) {

            }
            if (index != state.meals.lastIndex) {
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
            }
        }
    }
}