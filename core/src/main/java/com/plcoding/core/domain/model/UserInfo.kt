package com.plcoding.core.domain.model

//holds the data collected on the user in a single object
//easier gathering of info reference the user object instead of 10 diff funcs
data class UserInfo(
    val gender: Gender,
    val age: Int,
    val weight: Float,
    val height: Int,
    val activityLevel: ActivityLevel,
    val goalType: GoalType,
    val carbRatio: Float,
    val proteinRatio: Float,
    val fatRatio: Float,
)
