package com.h_fahmy.core_ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core.domain.model.GoalType
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.util.BaseLightPreview

@Composable
fun GoalTypeSelector(
    selectedGoalType: GoalType,
    onGoalTypeSelected: (GoalType) -> Unit,
) {
    val spacing = LocalSpacing.current

    Row {
        SelectableButton(
            text = stringResource(id = R.string.lose),
            selected = selectedGoalType == GoalType.LoseWeight,
            onClick = {
                onGoalTypeSelected(GoalType.LoseWeight)
            }
        )

        Spacer(modifier = Modifier.padding(spacing.spaceSmall))

        SelectableButton(
            text = stringResource(id = R.string.keep),
            selected = selectedGoalType == GoalType.KeepWeight,
            onClick = {
                onGoalTypeSelected(GoalType.KeepWeight)
            }
        )

        Spacer(modifier = Modifier.padding(spacing.spaceSmall))

        SelectableButton(
            text = stringResource(id = R.string.gain),
            selected = selectedGoalType == GoalType.GainWeight,
            onClick = {
                onGoalTypeSelected(GoalType.GainWeight)
            }
        )
    }
}

@Preview
@Composable
fun GoalTypeSelectorPreview() {
    BaseLightPreview {
        GoalTypeSelector(selectedGoalType = GoalType.LoseWeight, onGoalTypeSelected = {})
    }
}