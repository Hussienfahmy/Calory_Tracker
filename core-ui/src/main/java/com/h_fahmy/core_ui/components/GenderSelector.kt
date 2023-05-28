package com.h_fahmy.core_ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core.domain.model.Gender
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.util.BaseLightPreview

@Composable
fun GenderSelector(
    selectedGender: Gender,
    onGenderSelected: (Gender) -> Unit,
) {
    val spacing = LocalSpacing.current

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

@Preview
@Composable
fun GenderSelectorPreview() {
    BaseLightPreview {
        GenderSelector(selectedGender = Gender.Male, onGenderSelected = {})
    }
}