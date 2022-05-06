package com.plcoding.tracker_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Contains the model for the data that will be saved into the local db
 */
@Entity
data class TrackedFoodEntity(
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val imageUrl: String?,
    val type: String, //snack etc.
    val amount: Int, //grams
    val dayOfMonth: Int,
    val month: Int,
    val year: Int,
    val calories: Int,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
