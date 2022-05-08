package com.plcoding.tracker_domain.repository.use_cases

/**
 * wrapper class for all of the use cases so we are not injecting 5 diffrent use case classes into
 * the viewModels constructor
 */
data class TrackerUseCases(
    val trackFood: TrackFood,
    val searchFood: SearchFood,
    val deleteTrackedFood: DeleteTrackedFood,
    val getFoodsForDate: GetFoodsForDate,
    val calculateMealNutrients: CalculateMealNutrients
)