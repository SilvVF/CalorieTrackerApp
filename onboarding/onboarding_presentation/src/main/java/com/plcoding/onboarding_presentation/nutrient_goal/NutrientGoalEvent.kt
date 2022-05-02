package com.plcoding.onboarding_presentation.nutrient_goal

/**
 * class defines all of the actions that the user can make on the nutrient screen
 * helps keep the viewModel clean and parse these events
 */

sealed class NutrientGoalEvent {

    data class OnCarbRatioEnter(val ratio: String): NutrientGoalEvent()
    data class OnFatRatioEnter(val ratio: String): NutrientGoalEvent()
    data class OnProteinRatioEnter(val ratio: String): NutrientGoalEvent()

    object OnNextClick: NutrientGoalEvent()
}

