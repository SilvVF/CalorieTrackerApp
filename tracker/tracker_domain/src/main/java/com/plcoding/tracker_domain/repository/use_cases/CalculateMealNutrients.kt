package com.plcoding.tracker_domain.repository.use_cases

import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.domain.model.UserInfo
import com.plcoding.core.data.preferences.Preferences
import com.plcoding.tracker_domain.model.MealType
import com.plcoding.tracker_domain.model.TrackedFood
import kotlin.math.roundToInt

/**
 * gets all of the nutrient info that is needed in the UI
 *
 * map key = mealType
 *     value = data class that provides the value
 */
class CalculateMealNutrients(
    private val preferences: Preferences
) {

    operator fun invoke(trackedFoods: List<TrackedFood>): Result {
         val allNutrients = trackedFoods
             .groupBy { it.mealType }
             .mapValues { entry ->
                 val type = entry.key
                 val foods = entry.value
                 MealNutrients(
                     carbs = foods.sumOf { it.carbs },
                     protein = foods.sumOf { it.protein },
                     fat = foods.sumOf { it.fat },
                     calories = foods.sumOf { it.calories },
                     mealType = type
                 )
             }
        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalFat = allNutrients.values.sumOf { it.fat }
        val totalProtein = allNutrients.values.sumOf { it.protein }
        val totalCalories = allNutrients.values.sumOf { it.calories }
        val userInfo = preferences.loadUserInfo()
        val caloriesGoal = dailyCalorieRequirement(userInfo)
        val carbsGoal = (caloriesGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (caloriesGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (caloriesGoal * userInfo.fatRatio / 9f).roundToInt()

        return Result(
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            caloriesGoal = caloriesGoal,
            totalCarbs = totalCarbs,
            totalProtein = totalProtein,
            totalFat = totalFat,
            totalCalories = totalCalories,
            mealNutrients = allNutrients
        )
    }

    /**
     * return: Calculates the calories burned if you were to not move at all
     */
    private fun bmr(userInfo: UserInfo): Int {
        return when(userInfo.gender) {
            is Gender.Male -> {
                (66.47f + 13.75f + userInfo.weight +
                        5f * userInfo.height - 6.75f + userInfo.age).roundToInt()
            }
            is Gender.Female -> {
                (665.89f + 9.56f + userInfo.weight +
                        1.84f * userInfo.height - 4.67f + userInfo.age).roundToInt()
            }
        }
    }

    /**
     * return: the calorie amount needed based on activity level and goal type
     * not accurate but gives an idea
     */
    private fun dailyCalorieRequirement(userInfo: UserInfo): Int {
        val activityFactor = when(userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }
        val calorieExtra = when(userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 500
        }
        return (bmr(userInfo) * activityFactor + calorieExtra).roundToInt()
    }

    data class MealNutrients (
        val carbs: Int,
        val protein: Int,
        val fat: Int,
        val calories: Int,
        val mealType: MealType
    )

    data class Result(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val caloriesGoal: Int,
        val totalCarbs: Int,
        val totalFat: Int,
        val totalProtein: Int,
        val totalCalories: Int,
        val mealNutrients: Map<MealType, MealNutrients>
    )
}