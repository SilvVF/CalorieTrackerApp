package com.plcoding.tracker_data.mapper

import com.plcoding.tracker_data.local.entity.TrackedFoodEntity
import com.plcoding.tracker_domain.model.MealType
import com.plcoding.tracker_domain.model.TrackedFood
import java.time.LocalDate


/**
 * maps the tracked food object that is stored in the db from an entity data layer
 * to a tracked food domain object and vice versa
 */
fun TrackedFoodEntity.toTrackedFood(): TrackedFood{

    val mealType = MealType.fromString(type)
    val date = LocalDate.of(year, month, dayOfMonth)

    return TrackedFood(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        mealType = mealType,
        amount = amount,
        date = date,
        calories = calories,
        id = id
    )
}

fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        type = mealType.name,
        amount = amount,
        month = date.monthValue,
        dayOfMonth = date.dayOfMonth,
        year = date.year,
        calories = calories,
        id = id
    )
}