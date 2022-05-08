package com.plcoding.tracker_domain.repository.use_cases

import com.plcoding.tracker_domain.model.TrackableFood
import com.plcoding.tracker_domain.repository.TrackerRepository

/**
 * use case for searching for food search query
 */
class SearchFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40,
    ): Result<List<TrackableFood>> {
        if (query.isBlank()) return Result.success(emptyList())

        return repository.searchFood(query.trim(), page, pageSize)
    }
}