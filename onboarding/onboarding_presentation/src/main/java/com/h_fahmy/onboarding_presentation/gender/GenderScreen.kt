package com.h_fahmy.onboarding_presentation.gender

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
import com.h_fahmy.core.domain.model.Gender
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.UiEventHandler
import com.h_fahmy.core_ui.util.BaseLightPreview
import com.h_fahmy.onboarding_presentation.components.ActionButton
import com.h_fahmy.onboarding_presentation.components.SelectableButton

@Composable
fun GenderScreen(
    OnNextClick: () -> Unit,
    viewModel: GenderViewModel = hiltViewModel()
) {
    UiEventHandler(
        onSuccess = OnNextClick,
        uiEvent = viewModel.uiEvent,
    )

    GenderScreenContent(
        onNextClick = { viewModel.onNextClick() },
        onGenderSelected = viewModel::onGenderClick,
        selectedGender = viewModel.selectedGender
    )
}

@Composable
fun GenderScreenContent(
    onNextClick: () -> Unit,
    onGenderSelected: (Gender) -> Unit,
    selectedGender: Gender,
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
                text = stringResource(id = R.string.whats_your_gender),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            Row {
                SelectableButton(
                    text = stringResource(id = R.string.male),
                    selected = selectedGender == Gender.Male,
                    onClick = {
                        onGenderSelected(Gender.Male)
                    }
                )

                Spacer(modifier = Modifier.padding(spacing.spaceSmall))

                SelectableButton(
                    text = stringResource(id = R.string.female),
                    selected = selectedGender == Gender.Female,
                    onClick = {
                        onGenderSelected(Gender.Female)
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
        GenderScreenContent(
            selectedGender = Gender.Male,
            onGenderSelected = {},
            onNextClick = {}
        )
    }
}