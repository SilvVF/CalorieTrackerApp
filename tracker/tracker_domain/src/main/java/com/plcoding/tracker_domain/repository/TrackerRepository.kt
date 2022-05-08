package com.plcoding.tracker_domain.repository

import com.plcoding.tracker_domain.model.TrackableFood
import com.plcoding.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

/**
 * repository for the domain layer Tracked food - db and Trackable food - api
 *
 * doesn't define the implementation only shows what we need
 * allows the creation of fake implementation making testing easier
 *
 * Each function that can be performed in the repository should have a use case created
 */
interface TrackerRepository {

    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int,
    ): Result<List<TrackableFood>>

    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food: TrackedFood)

    fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>>
}