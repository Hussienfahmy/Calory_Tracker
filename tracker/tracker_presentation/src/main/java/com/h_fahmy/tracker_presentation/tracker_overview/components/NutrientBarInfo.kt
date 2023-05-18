package com.h_fahmy.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core_ui.theme.ProteinColor
import com.h_fahmy.core_ui.util.BaseLightPreview
import com.h_fahmy.tracker_presentation.components.UnitDisplay

@Composable
fun NutrientBarInfo(
    value: Int,
    goal: Int,
    name: String,
    color: Color,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 8.dp,
) {
    val background = MaterialTheme.colorScheme.background
    val goalExceedColor = MaterialTheme.colorScheme.error

    val angleRatio = remember { Animatable(0f) }

    LaunchedEffect(value) {
        angleRatio.animateTo(
            targetValue = if (goal > 0) {
                value / goal.toFloat()
            } else 0f,
            animationSpec = tween(
                durationMillis = 300,
            )
        )
    }

    NutrientBarInfoContent(
        modifier = modifier,
        value = value,
        goal = goal,
        background = background,
        goalExceedColor = goalExceedColor,
        strokeWidth = strokeWidth,
        color = color,
        angleRatio = angleRatio.value,
        name = name
    )
}

@Composable
private fun NutrientBarInfoContent(
    modifier: Modifier,
    value: Int,
    goal: Int,
    background: Color,
    goalExceedColor: Color,
    strokeWidth: Dp,
    color: Color,
    angleRatio: Float,
    name: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            drawArc(
                color = if (value <= goal) background else goalExceedColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round,
                )
            )

            if (value <= goal) {
                drawArc(
                    color = color,
                    startAngle = 90f,
                    sweepAngle = angleRatio * 360f,
                    useCenter = false,
                    size = size,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round,
                    )
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UnitDisplay(
                amount = value,
                unit = stringResource(id = R.string.grams),
                textColor = if (value <= goal) MaterialTheme.colorScheme.onPrimary else goalExceedColor,
            )

            Text(
                text = name,
                color = if (value <= goal) MaterialTheme.colorScheme.onPrimary else goalExceedColor,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Light,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NutrientBarInfoContentPreview() {
    BaseLightPreview {
        NutrientBarInfoContent(
            modifier = Modifier
                .size(200.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            value = 100,
            goal = 200,
            background = MaterialTheme.colorScheme.background,
            goalExceedColor = MaterialTheme.colorScheme.error,
            strokeWidth = 4.dp,
            color = ProteinColor,
            angleRatio = 100 / 200f,
            name = "Protein"
        )
    }
}