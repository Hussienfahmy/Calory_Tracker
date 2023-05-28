package com.h_fahmy.onboarding_presentation.weight

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
import com.h_fahmy.core_ui.components.UnitTextField
import com.h_fahmy.core_ui.util.BaseLightPreview
import com.h_fahmy.onboarding_presentation.components.ActionButton

@Composable
fun WeightScreen(
    OnNextClick: () -> Unit,
    viewModel: WeightViewModel = hiltViewModel(),
    snackBarHostState: SnackbarHostState,
) {

    UiEventHandler(
        onSuccess = OnNextClick,
        uiEvent = viewModel.uiEvent,
        snackBarHostState = snackBarHostState
    )

    WeightcreenContent(
        onWeightChange = { viewModel.onWeightEnter(it) },
        onNextClick = { viewModel.onNextClick() },
        weight = viewModel.weight,
    )
}

@Composable
private fun WeightcreenContent(
    onWeightChange: (String) -> Unit,
    onNextClick: () -> Unit,
    weight: String,
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
                text = stringResource(id = R.string.whats_your_weight),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            UnitTextField(
                value = weight,
                onValueChange = onWeightChange,
                unit = stringResource(id = R.string.kg)
            )
        }

        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = stringResource(id = R.string.next),
            onClick = onNextClick
        )
    }
}

@Preview
@Composable
fun GenderScreenPreview() {
    BaseLightPreview {
        WeightcreenContent(onWeightChange = {}, onNextClick = { /**/ }, weight = "80.0")
    }
}