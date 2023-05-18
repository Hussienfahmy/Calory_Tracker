package com.h_fahmy.tracker_presentation.tracker_overview.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.h_fahmy.core_ui.theme.CarbColor
import com.h_fahmy.core_ui.theme.FatColor
import com.h_fahmy.core_ui.theme.ProteinColor
import com.h_fahmy.core_ui.util.BaseDarkPreview
import com.h_fahmy.core_ui.util.BaseLightPreview

@Composable
fun NutrientsBar(
    carbs: Int,
    protein: Int,
    fat: Int,
    calories: Int,
    calorieGoal: Int,
    modifier: Modifier = Modifier,
) {
    val background = MaterialTheme.colorScheme.background
    val caloriesExceedColor = MaterialTheme.colorScheme.error
    val cornerRadius = CornerRadius(100f)

    val carbWidthRatio = remember { Animatable(0f) }
    val proteinWidthRatio = remember { Animatable(0f) }
    val fatWidthRatio = remember { Animatable(0f) }

    LaunchedEffect(carbs) {
        carbWidthRatio.animateTo(
            targetValue = ((carbs * 4f) / calorieGoal)
        )
    }

    LaunchedEffect(protein) {
        proteinWidthRatio.animateTo(
            targetValue = ((protein * 4f) / calorieGoal)
        )
    }

    LaunchedEffect(fat) {
        fatWidthRatio.animateTo(
            targetValue = ((fat * 9f) / calorieGoal)
        )
    }

    NutrientBarContent(
        modifier,
        calories,
        calorieGoal,
        carbWidthRatio.value,
        proteinWidthRatio.value,
        fatWidthRatio.value,
        background,
        cornerRadius,
        caloriesExceedColor
    )
}

@Composable
private fun NutrientBarContent(
    modifier: Modifier = Modifier,
    calories: Int,
    calorieGoal: Int,
    carbWidthRatio: Float,
    proteinWidthRatio: Float,
    fatWidthRatio: Float,
    background: Color,
    cornerRadius: CornerRadius,
    caloriesExceedColor: Color
) {
    Canvas(modifier = modifier) {
        if (calories <= calorieGoal) {
            val carbsWidth = carbWidthRatio * size.width
            val proteinWidth = proteinWidthRatio * size.width
            val fatWidth = fatWidthRatio * size.width

            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = cornerRadius
            )

            drawRoundRect(
                color = FatColor,
                size = size.copy(
                    width = carbsWidth + proteinWidth + fatWidth,
                    height = size.height
                ),
                cornerRadius = cornerRadius
            )

            drawRoundRect(
                color = ProteinColor,
                size = size.copy(
                    width = carbsWidth + proteinWidth,
                    height = size.height
                ),
                cornerRadius = cornerRadius
            )

            drawRoundRect(
                color = CarbColor,
                size = size.copy(
                    width = carbsWidth,
                    height = size.height
                ),
                cornerRadius = cornerRadius
            )
        } else {
            drawRoundRect(
                color = caloriesExceedColor,
                size = size,
                cornerRadius = cornerRadius
            )
        }
    }
}

@Composable
private fun NutrientsBarBasePreview() {
    val carbs = 5
    val protein = 5
    val fat = 5

    val carbsWidthRatio = ((carbs * 4f) / 100)
    val proteinWidthRatio = ((protein * 4f) / 100)
    val fatWidthRatio = ((fat * 9f) / 100)

    NutrientBarContent(
        modifier = Modifier.sizeIn(minWidth = 200.dp, minHeight = 50.dp),
        calories = 80,
        calorieGoal = 100,
        carbWidthRatio = carbsWidthRatio,
        proteinWidthRatio = proteinWidthRatio,
        fatWidthRatio = fatWidthRatio,
        background = MaterialTheme.colorScheme.background,
        cornerRadius = CornerRadius(100f),
        caloriesExceedColor = MaterialTheme.colorScheme.error
    )
}

@Preview(showBackground = true)
@Composable
fun NutrientsBarPreviewLight() {
    BaseLightPreview {
        Surface(modifier = Modifier.padding(10.dp)) {
            NutrientsBarBasePreview()
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NutrientsBarPreviewDark() {
    BaseDarkPreview {
        Surface(modifier = Modifier.padding(10.dp)) {
            NutrientsBarBasePreview()
        }
    }
}