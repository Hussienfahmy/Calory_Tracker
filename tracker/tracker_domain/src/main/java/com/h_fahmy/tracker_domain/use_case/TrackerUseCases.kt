package com.h_fahmy.tracker_domain.use_case

data class TrackerUseCases(
    val trackFood: TrackFood,
    val searchFood: SearchFood,
    val getFoodsByDate: GetFoodsByDate,
    val deleteTrackedFood: DeleteTrackedFood,
    val calculateMealNutrients: CalculateMealNutrients,
)
