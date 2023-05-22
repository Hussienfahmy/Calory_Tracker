package com.h_fahmy.tracker_presentation.tracker_overview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.util.BaseLightPreview
import com.h_fahmy.core_ui.util.UiText
import com.h_fahmy.tracker_domain.model.MealType
import com.h_fahmy.tracker_presentation.components.UnitDisplay
import com.h_fahmy.tracker_presentation.tracker_overview.Meal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableMeal(
    modifier: Modifier = Modifier,
    meal: Meal,
    onToggleClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    Card(onClick = onToggleClick, modifier = modifier) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceMedium), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = meal.drawableRes),
                    contentDescription = meal.name.asString(context)
                )

                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = meal.name.asString(context),
                            style = MaterialTheme.typography.displaySmall
                        )

                        Icon(
                            imageVector = if (meal.isExpanded) Icons.Default.KeyboardArrowUp
                            else Icons.Default.KeyboardArrowDown,
                            contentDescription = stringResource(
                                id = if (meal.isExpanded) R.string.collapse else R.string.extend
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(spacing.spaceSmall))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        UnitDisplay(
                            unit = stringResource(id = R.string.kcal),
                            amount = meal.caloriesAmount,
                        )

                        Spacer(modifier = Modifier.width(spacing.spaceMedium))

                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            NutrientInfo(
                                name = stringResource(id = R.string.carbs),
                                unit = stringResource(id = R.string.grams),
                                amount = meal.carbsAmount
                            )

                            NutrientInfo(
                                name = stringResource(id = R.string.protein),
                                unit = stringResource(id = R.string.grams),
                                amount = meal.proteinAmount
                            )

                            NutrientInfo(
                                name = stringResource(id = R.string.fat),
                                unit = stringResource(id = R.string.grams),
                                amount = meal.fatAmount
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            AnimatedVisibility(visible = meal.isExpanded) {
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandableMealPreview() {
    BaseLightPreview(modifier = Modifier.padding(16.dp)) {
        ExpandableMeal(meal = Meal(
            name = UiText.DynamicString("Breakfast"),
            drawableRes = R.drawable.ic_breakfast,
            mealType = MealType.BREAKFAST,
            carbsAmount = 200,
            proteinAmount = 150,
            fatAmount = 120,
            caloriesAmount = 1200,
            isExpanded = false
        ), onToggleClick = {}, content = {})
    }
}