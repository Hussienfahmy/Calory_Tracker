package com.h_fahmy.core_ui.domain.use_case

import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core_ui.util.UiText

class ValidateNutrients {
    operator fun invoke(
        carbsRatio: String,
        proteinRatio: String,
        fatRatio: String
    ): Result {
        val carbs = carbsRatio.toIntOrNull()
        val protein = proteinRatio.toIntOrNull()
        val fat = fatRatio.toIntOrNull()

        if (carbs == null || protein == null || fat == null) {
            return Result.Error(
                message = UiText.StringResource(R.string.error_invalid_values)
            )
        }

        if (carbs + protein + fat != 100) {
            return Result.Error(
                message = UiText.StringResource(R.string.error_not_100_percent)
            )
        }

        return Result.Success(
            carbsRatio = carbs / 100f,
            proteinRatio = protein / 100f,
            fatRatio = fat / 100f
        )
    }

    sealed class Result {
        data class Success(
            val carbsRatio: Float,
            val proteinRatio: Float,
            val fatRatio: Float
        ) : Result()

        data class Error(val message: UiText) : Result()
    }
}