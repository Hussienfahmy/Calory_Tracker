package com.h_fahmy.onboarding_presentation.nutrient_goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.UiEventHandler
import com.h_fahmy.core_ui.util.BaseLightPreview
import com.h_fahmy.core_ui.util.UiEvent
import com.h_fahmy.onboarding_presentation.components.ActionButton
import com.h_fahmy.onboarding_presentation.components.UnitTextField

@Composable
fun NutrientGoalScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: NutrientGoalViewModel = hiltViewModel(),
    snackBarHostState: SnackbarHostState,
) {

    UiEventHandler(
        onNavigate = onNavigate,
        uiEvent = viewModel.uiEvent,
        snackBarHostState = snackBarHostState
    )

    NutrientGoalScreenContent(
        onEvent = viewModel::onEvent,
        nutrientGoalState = viewModel.state
    )
}

@Composable
private fun NutrientGoalScreenContent(
    onEvent: (NutrientGoalEvent) -> Unit,
    nutrientGoalState: NutrientGoalState
) {
    val spacing = LocalSpacing.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            UnitTextField(
                value = nutrientGoalState.carbsRatio,
                onValueChange = {
                    onEvent(NutrientGoalEvent.OnCarbsRationEntered(it))
                },
                unit = stringResource(id = R.string.percent_carbs)
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            UnitTextField(
                value = nutrientGoalState.proteinRatio,
                onValueChange = {
                    onEvent(NutrientGoalEvent.OnProteinRationEntered(it))
                },
                unit = stringResource(id = R.string.percent_proteins)
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            UnitTextField(
                value = nutrientGoalState.fatRatio,
                onValueChange = {
                    onEvent(NutrientGoalEvent.OnFatRationEntered(it))
                },
                unit = stringResource(id = R.string.percent_fats)
            )
        }

        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = stringResource(id = R.string.next),
            onClick = {
                onEvent(NutrientGoalEvent.OnNextClicked)
            }
        )
    }
}

@Preview
@Composable
fun GenderScreenPreview() {
    BaseLightPreview {
        NutrientGoalScreenContent(onEvent = {}, nutrientGoalState = NutrientGoalState())
    }
}