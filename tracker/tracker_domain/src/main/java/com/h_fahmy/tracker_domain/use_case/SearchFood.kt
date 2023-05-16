package com.h_fahmy.tracker_domain.use_case

import com.h_fahmy.tracker_domain.model.TrackableFood
import com.h_fahmy.tracker_domain.repository.TrackerRepository

class SearchFood(
    private val repository: TrackerRepository,
) {

    suspend operator fun invoke(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        if (query.isBlank()) {
            return Result.success(emptyList())
        }

        return repository.searchFood(query.trim(), page, pageSize)
    }
}