package com.h_fahmy.tracker_domain.use_case

import com.h_fahmy.tracker_domain.model.TrackedFood
import com.h_fahmy.tracker_domain.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository,
) {

    suspend operator fun invoke(trackFood: TrackedFood) {
        return repository.deleteTrackedFood(trackFood)
    }
}