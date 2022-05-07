package com.plcoding.tracker_domain.model

import java.time.LocalDate

/**
 * domain layer model for the database entities
 *
 * in this class sealed classes and other objects can be stored
 * this class does not need to hold only primitives because it is not directly parsed
 * by the database or API
 */
data class TrackedFood (
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val imageUrl: String?,
    val mealType: MealType, //in the domain we can store more convenient objects
    val amount: Int,
    val date: LocalDate,
    val calories: Int,
    val id: Int? = null,
)