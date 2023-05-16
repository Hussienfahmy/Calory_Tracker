package com.h_fahmy.tracker_domain.use_case

import com.h_fahmy.tracker_domain.model.TrackedFood
import com.h_fahmy.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsByDate(
    private val repository: TrackerRepository,
) {

    operator fun invoke(
        date: LocalDate
    ): Flow<List<TrackedFood>> {
        return repository.getFoodsByDate(date)
    }
}