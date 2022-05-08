package com.plcoding.tracker_domain.repository.use_cases


import com.plcoding.tracker_domain.model.TrackedFood
import com.plcoding.tracker_domain.repository.TrackerRepository

/**
 * use case for searching for food search query
 */
class DeleteTrackedFood(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(food: TrackedFood) = repository.deleteTrackedFood(food)
}