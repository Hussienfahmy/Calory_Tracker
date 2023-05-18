package com.h_fahmy.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.theme.CarbColor
import com.h_fahmy.core_ui.theme.FatColor
import com.h_fahmy.core_ui.theme.ProteinColor
import com.h_fahmy.core_ui.util.BaseLightPreview
import com.h_fahmy.tracker_presentation.components.UnitDisplay
import com.h_fahmy.tracker_presentation.tracker_overview.TrackerOverviewState

@Composable
fun NutrientHeader(
    state: TrackerOverviewState,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current

    val animatedCalorieCount by animateIntAsState(targetValue = state.totalCalories)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                )
            )
            .background(MaterialTheme.colorScheme.primary)
            .padding(
                horizontal = spacing.spaceLarge,
                vertical = spacing.spaceExtraLarge
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            UnitDisplay(
                unit = stringResource(id = R.string.kcal),
                amount = animatedCalorieCount,
                textColor = MaterialTheme.colorScheme.onPrimary,
                amountTextSize = 40.sp,
                unitTextSize = 20.sp,
                modifier = Modifier.align(Alignment.Bottom)
            )

            Column {
                Text(
                    text = stringResource(id = R.string.your_goal),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium,
                )

                UnitDisplay(
                    unit = stringResource(id = R.string.kcal),
                    amount = state.caloriesGoal,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    amountTextSize = 40.sp,
                    unitTextSize = 20.sp,
                )
            }
        }

        Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))

        NutrientsBar(
            carbs = state.totalCarbs,
            protein = state.totalProtein,
            fat = state.totalFat,
            calories = state.totalCalories,
            calorieGoal = state.caloriesGoal,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )

        Spacer(modifier = Modifier.height(spacing.spaceLarge))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            NutrientBarInfo(
                value = state.totalCarbs,
                goal = state.carbsGoal,
                name = stringResource(id = R.string.carbs),
                color = CarbColor,
                modifier = Modifier.size(90.dp)
            )

            NutrientBarInfo(
                value = state.totalProtein,
                goal = state.proteinGoal,
                name = stringResource(id = R.string.protein),
                color = ProteinColor,
                modifier = Modifier.size(90.dp)
            )

            NutrientBarInfo(
                value = state.totalFat,
                goal = state.fatGoal,
                name = stringResource(id = R.string.fat),
                color = FatColor,
                modifier = Modifier.size(90.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NutrientHeaderPreview() {
    BaseLightPreview {
        NutrientHeader(
            state = TrackerOverviewState(
                totalCalories = 2000,
                caloriesGoal = 3000,
                totalCarbs = 100,
                carbsGoal = 200,
                totalProtein = 100,
                proteinGoal = 200,
                totalFat = 100,
                fatGoal = 200,
            )
        )
    }
}