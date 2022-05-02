package com.plcoding.on_boarding_domain.use_cases

import com.plcoding.core.util.UiText
import com.plcoding.onboarding_domain.R

/**
 * use case for validating data in the ratio entries in Nutrient screen / viewModel
 */
class ValidateNutrients {
    //validate something and need to inform the viewModel
    operator fun invoke(
        carbsRatioText: String,
        proteinRatioText: String,
        fatRatioText: String
    ): Result { //validation work
        val carbsRatio = carbsRatioText.toIntOrNull()
        val proteinRatio = proteinRatioText.toIntOrNull()
        val fatRatio = fatRatioText.toIntOrNull()
        if (carbsRatio == null || proteinRatio == null || fatRatio == null){
            return Result.Error(
                UiText.StringResource(R.string.error_invalid_values)
            )
        }
        if (carbsRatio + proteinRatio + fatRatio != 100){
            return Result.Error(
                UiText.StringResource(R.string.error_not_100_percent)
            )
        }
        return Result.Success( //divide by 100 to get the actual ratio
            carbsRatio / 100f,
            proteinRatio/ 100f,
            fatRatio / 100f
        )
    }

    //create a class to tell the viewModel the result
    sealed class Result {
        data class Success(
            val carbsRatio: Float,
            val proteinRatio: Float,
            val fatRatio: Float,
        ): Result()

        data class Error(val message: UiText): Result()
    }
}