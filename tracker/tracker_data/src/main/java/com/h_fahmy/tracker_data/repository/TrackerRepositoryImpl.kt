package com.h_fahmy.tracker_data.repository

import com.h_fahmy.tracker_data.local.TrackerDao
import com.h_fahmy.tracker_data.mapper.toTrackableFood
import com.h_fahmy.tracker_data.mapper.toTrackedFood
import com.h_fahmy.tracker_data.mapper.toTrackedFoodEntity
import com.h_fahmy.tracker_data.remote.OpenFoodAPI
import com.h_fahmy.tracker_domain.model.TrackableFood
import com.h_fahmy.tracker_domain.model.TrackedFood
import com.h_fahmy.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodAPI,
) : TrackerRepository {

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.search(query, page, pageSize)
            Result.success(searchDto.products.mapNotNull { it.toTrackableFood() })
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(trackedFood: TrackedFood) {
        dao.insertTrackedFood(trackedFood.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(trackedFood: TrackedFood) {
        dao.deleteTrackedFood(trackedFood.toTrackedFoodEntity())
    }

    override fun getFoodsByDate(date: LocalDate): Flow<List<TrackedFood>> {
        return dao.getTrackedFoodByDate(
            day = date.dayOfMonth,
            month = date.monthValue,
            year = date.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }
}