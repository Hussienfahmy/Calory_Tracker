package com.h_fahmy.tracker_presentation.search

import com.h_fahmy.tracker_domain.model.MealType
import com.h_fahmy.tracker_domain.model.TrackableFood
import java.time.LocalDate

sealed class SearchEvent {
    data class OnQueryChange(val query: String) : SearchEvent()
    object OnSearchClick : SearchEvent()
    data class OnToggleTrackableFood(val food: TrackableFood) : SearchEvent()
    data class OnAmountForFoodChange(val food: TrackableFood, val amount: Int) : SearchEvent()
    data class OnTrackFoodClick(
        val food: TrackableFood,
        val mealType: MealType,
        val date: LocalDate
    ) : SearchEvent()

    data class OnSearchFocusChange(val hasFocus: Boolean) : SearchEvent()
}
