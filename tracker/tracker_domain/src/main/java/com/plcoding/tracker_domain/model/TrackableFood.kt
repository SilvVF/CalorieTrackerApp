package com.plcoding.tracker_domain.model


/**
 * domain layer model class containing the items from teh response that are needed
 * throughout the entire application
 *
 * Data layer objects should not be accessed in any other layer in clean architecture
 * instead create domain models and use mappers to create these objects
 */
data class TrackableFood(
    val name: String,
    val imageUrl: String?,
    val caloriesPer100g: Int,
    val carbsPer100g: Int,
    val proteinPer100g: Int,
    val fatPer100g: Int,
)
