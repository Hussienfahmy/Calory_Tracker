package com.h_fahmy.profile_presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core.domain.model.ActivityLevel
import com.h_fahmy.core.domain.model.Gender
import com.h_fahmy.core.domain.model.GoalType
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.components.ActivityLevelSelector
import com.h_fahmy.core_ui.components.GenderSelector
import com.h_fahmy.core_ui.components.GoalTypeSelector
import com.h_fahmy.core_ui.util.BaseLightPreview
import com.h_fahmy.profile_domain.model.UserProfile
import com.h_fahmy.profile_presentation.ProfileEvent
import com.h_fahmy.profile_presentation.components.ProfileField
import com.h_fahmy.profile_presentation.components.ProfileTextField

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    val userProfile = state.userProfile
    if (userProfile != null) {
        ProfileScreenContent(
            userProfile = userProfile,
            onAgeChanged = { viewModel.onEvent(ProfileEvent.OnAgeChanged(it)) },
            onHeightChanged = { viewModel.onEvent(ProfileEvent.OnHeightChanged(it)) },
            onWeightChanged = { viewModel.onEvent(ProfileEvent.OnWeightChanged(it)) },
            onGenderChanged = { viewModel.onEvent(ProfileEvent.OnGenderChanged(it)) },
            onActivityLevelChanged = { viewModel.onEvent(ProfileEvent.OnActivityLevelChanged(it)) },
            onGoalTypeChanged = { viewModel.onEvent(ProfileEvent.OnGoalTypeChanged(it)) },
            onNutrientsGoalChanged = { carbRatio, proteinRatio, fatRatio ->
                viewModel.onEvent(
                    ProfileEvent.OnNutrientsGoalChanged(
                        carbRatio = carbRatio,
                        proteinRatio = proteinRatio,
                        fatRatio = fatRatio
                    )
                )
            },
            isCarbInvalid = state.invalidCarbRatio,
            isProteinInvalid = state.invalidProteinRatio,
            isFatInvalid = state.invalidFatRatio
        )
    }
}

@Composable
private fun ProfileScreenContent(
    userProfile: UserProfile,
    onAgeChanged: (String) -> Unit,
    onHeightChanged: (String) -> Unit,
    onWeightChanged: (String) -> Unit,
    onGenderChanged: (Gender) -> Unit,
    onActivityLevelChanged: (ActivityLevel) -> Unit,
    onGoalTypeChanged: (GoalType) -> Unit,
    onNutrientsGoalChanged: (carbRatio: String, proteinRatio: String, fatRatio: String) -> Unit,
    isCarbInvalid: Boolean,
    isProteinInvalid: Boolean,
    isFatInvalid: Boolean,
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceSmall)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
    ) {
        Card {
            Column {
                ProfileTextField(
                    title = stringResource(id = R.string.age),
                    value = userProfile.age.toString(),
                    unit = stringResource(id = R.string.years),
                    onValueChanged = onAgeChanged
                )

                ProfileTextField(
                    title = stringResource(id = R.string.height),
                    value = userProfile.height.toString(),
                    unit = stringResource(id = R.string.cm),
                    onValueChanged = onHeightChanged
                )

                ProfileTextField(
                    title = stringResource(id = R.string.weight),
                    value = userProfile.weight.toString(),
                    unit = stringResource(id = R.string.kg),
                    onValueChanged = onWeightChanged
                )
            }
        }

        Card {
            Column {
                ProfileTextField(
                    title = stringResource(id = R.string.carbs),
                    value = userProfile.carbRatio.toString(),
                    unit = stringResource(id = R.string.percent),
                    onValueChanged = {
                        onNutrientsGoalChanged(
                            it,
                            userProfile.proteinRatio.toString(),
                            userProfile.fatRatio.toString()
                        )
                    },
                    error = isCarbInvalid
                )

                ProfileTextField(
                    title = stringResource(id = R.string.prtein),
                    value = userProfile.proteinRatio.toString(),
                    unit = stringResource(id = R.string.percent),
                    onValueChanged = {
                        onNutrientsGoalChanged(
                            userProfile.carbRatio.toString(),
                            it,
                            userProfile.fatRatio.toString()
                        )
                    },
                    error = isProteinInvalid
                )

                ProfileTextField(
                    title = stringResource(id = R.string.fat),
                    value = userProfile.fatRatio.toString(),
                    unit = stringResource(id = R.string.percent),
                    onValueChanged = {
                        onNutrientsGoalChanged(
                            userProfile.carbRatio.toString(),
                            userProfile.proteinRatio.toString(),
                            it
                        )
                    },
                    error = isFatInvalid
                )
            }
        }

        Card {
            Column {

                ProfileField(title = stringResource(id = R.string.gender)) {
                    GenderSelector(
                        selectedGender = userProfile.gender,
                        onGenderSelected = onGenderChanged
                    )
                }

                ProfileField(title = stringResource(id = R.string.activityLevel)) {
                    ActivityLevelSelector(
                        selectedActivityLevel = userProfile.activityLevel,
                        onActivityLevelSelected = onActivityLevelChanged
                    )
                }

                ProfileField(title = stringResource(id = R.string.goal)) {
                    GoalTypeSelector(
                        selectedGoalType = userProfile.goalType,
                        onGoalTypeSelected = onGoalTypeChanged
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenContentPreview() {
    BaseLightPreview {
        ProfileScreenContent(
            userProfile = UserProfile(
                age = 24,
                height = 176,
                weight = 95f,
                gender = Gender.Male,
                activityLevel = ActivityLevel.Medium,
                goalType = GoalType.LoseWeight,
                carbRatio = 50,
                proteinRatio = 30,
                fatRatio = 20,

                ),
            onAgeChanged = {},
            onHeightChanged = {},
            onWeightChanged = {},
            onGenderChanged = {},
            onActivityLevelChanged = {},
            onGoalTypeChanged = {},
            onNutrientsGoalChanged = { _, _, _ -> },
            isCarbInvalid = false,
            isProteinInvalid = true,
            isFatInvalid = false
        )
    }
}