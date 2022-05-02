package com.plcoding.onboarding_presentation.nutrient_goal


//single ui state to observe
//prevents the viewModel from having to many state variables
data class NutrientGoalState(
    val carbsRatio: String = "40",
    val proteinRatio: String = "30",
    val fatRatio: String = "30"
)
