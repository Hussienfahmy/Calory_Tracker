package com.h_fahmy.onboarding_presentation.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core.domain.model.ActivityLevel
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.UiEventHandler
import com.h_fahmy.core_ui.util.BaseLightPreview
import com.h_fahmy.core_ui.util.UiEvent
import com.h_fahmy.onboarding_presentation.components.ActionButton
import com.h_fahmy.onboarding_presentation.components.SelectableButton

@Composable
fun ActivityScreen(
    onNavigate: (UiEvent) -> Unit,
    viewModel: ActivityViewModel = hiltViewModel()
) {
    UiEventHandler(
        onNavigate = onNavigate,
        uiEvent = viewModel.uiEvent,
    )

    ActivityScreenContent(
        onNextClick = { viewModel.onNextClick() },
        onActivityLevelSelected = viewModel::onActivityLevelSelected,
        selectedActivityLevel = viewModel.selectedActivityLevel
    )
}

@Composable
fun ActivityScreenContent(
    onNextClick: () -> Unit,
    onActivityLevelSelected: (ActivityLevel) -> Unit,
    selectedActivityLevel: ActivityLevel,
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
                text = stringResource(id = R.string.whats_your_activity_level),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            Row {
                SelectableButton(
                    text = stringResource(id = R.string.low),
                    selected = selectedActivityLevel == ActivityLevel.Low,
                    onClick = {
                        onActivityLevelSelected(ActivityLevel.Low)
                    }
                )

                Spacer(modifier = Modifier.padding(spacing.spaceSmall))

                SelectableButton(
                    text = stringResource(id = R.string.medium),
                    selected = selectedActivityLevel == ActivityLevel.Medium,
                    onClick = {
                        onActivityLevelSelected(ActivityLevel.Medium)
                    }
                )

                Spacer(modifier = Modifier.padding(spacing.spaceSmall))

                SelectableButton(
                    text = stringResource(id = R.string.high),
                    selected = selectedActivityLevel == ActivityLevel.High,
                    onClick = {
                        onActivityLevelSelected(ActivityLevel.High)
                    }
                )
            }
        }

        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = stringResource(id = R.string.next),
            onClick = onNextClick
        )
    }
}

@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun GenderScreenPreview() {
    BaseLightPreview {
        ActivityScreenContent(
            onNextClick = {},
            onActivityLevelSelected = {},
            selectedActivityLevel = ActivityLevel.Medium
        )
    }
}