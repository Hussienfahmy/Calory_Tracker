package com.h_fahmy.onboarding_presentation.goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.h_fahmy.core.domain.model.GoalType
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.UiEventHandler
import com.h_fahmy.core_ui.components.GoalTypeSelector
import com.h_fahmy.core_ui.util.BaseLightPreview
import com.h_fahmy.onboarding_presentation.components.ActionButton

@Composable
fun GoalScreen(
    OnNextClick: () -> Unit,
    viewModel: GoalViewModel = hiltViewModel()
) {
    UiEventHandler(
        onSuccess = OnNextClick,
        uiEvent = viewModel.uiEvent,
    )

    GoalScreenContent(
        onNextClick = { viewModel.onNextClick() },
        onGoalTypeSelected = viewModel::onGoalTypeSelected,
        selectedGoalType = viewModel.selectedGoal
    )
}

@Composable
fun GoalScreenContent(
    onNextClick: () -> Unit,
    onGoalTypeSelected: (GoalType) -> Unit,
    selectedGoalType: GoalType,
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
                text = stringResource(id = R.string.your_goal),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            GoalTypeSelector(selectedGoalType, onGoalTypeSelected)
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
        GoalScreenContent(
            onNextClick = {},
            onGoalTypeSelected = {},
            selectedGoalType = GoalType.KeepWeight
        )
    }
}