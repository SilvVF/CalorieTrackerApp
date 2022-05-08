package com.plcoding.tracker_domain.repository.use_cases

import com.plcoding.tracker_domain.model.MealType
import com.plcoding.tracker_domain.model.TrackableFood
import com.plcoding.tracker_domain.model.TrackedFood
import com.plcoding.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

/**
 * use case for searching for food search query
 */
class TrackFood(
    private val repository: TrackerRepository
) {

    private fun Int.roundNutrientInfo(amount: Int): Int{
        return ((this / 100f) * amount).roundToInt()
    }

    suspend operator fun invoke(
        food: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate
    ) {
        repository.insertTrackedFood(
            TrackedFood(
                name = food.name,
                carbs = food.carbsPer100g.roundNutrientInfo(amount),
                protein = food.proteinPer100g.roundNutrientInfo(amount),
                fat = food.fatPer100g.roundNutrientInfo(amount),
                calories = food.caloriesPer100g.roundNutrientInfo(amount),
                imageUrl = food.imageUrl,
                mealType = mealType,
                amount = amount,
                date = date
            )
        )
    }
}