package com.h_fahmy.tracker_presentation.tracker_overview

import com.h_fahmy.tracker_domain.model.TrackedFood

sealed class TrackerOverviewEvent {
    object OnNextDayClicked : TrackerOverviewEvent()
    object OnPreviousDayClicked : TrackerOverviewEvent()
    data class OnToggleMealClicked(val meal: Meal) : TrackerOverviewEvent()
    data class DeleteTrackedFood(val trackedFood: TrackedFood) : TrackerOverviewEvent()
    data class OnAddFoodClicked(val meal: Meal) : TrackerOverviewEvent()
}
