package com.h_fahmy.core_ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core.domain.model.ActivityLevel
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.util.BaseLightPreview

@Composable
fun ActivityLevelSelector(
    selectedActivityLevel: ActivityLevel,
    onActivityLevelSelected: (ActivityLevel) -> Unit,
) {
    val spacing = LocalSpacing.current

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

@Preview
@Composable
fun ActivitySelectorPreview() {
    BaseLightPreview {
        ActivityLevelSelector(
            selectedActivityLevel = ActivityLevel.Medium,
            onActivityLevelSelected = {})
    }
}