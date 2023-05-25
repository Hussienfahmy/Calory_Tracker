@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.h_fahmy.tracker_presentation.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.modifier_ext.borderLineBottom
import com.h_fahmy.core_ui.util.BaseDarkPreview
import com.h_fahmy.core_ui.util.BaseLightPreview
import com.h_fahmy.tracker_domain.model.TrackableFood
import com.h_fahmy.tracker_presentation.search.TrackableFoodUiState
import com.h_fahmy.tracker_presentation.tracker_overview.components.NutrientInfo
import com.h_fahmy.tracker_presentation.tracker_overview.util.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackableFoodItem(
    trackableFoodUiState: TrackableFoodUiState,
    onClick: () -> Unit,
    onAmountChange: (String) -> Unit,
    onTrack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    val food = trackableFoodUiState.food

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(spacing.spaceExtraSmall)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() }
            .padding(end = spacing.spaceMedium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(1f)) {
                Image(
                    painter = rememberAsyncImagePainter(
                        data = food.imageUrl,
                        builder = {
                            crossfade(true)
                            error(R.drawable.ic_burger)
                            fallback(R.drawable.ic_burger)
                        }
                    ),
                    contentDescription = food.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(topStart = 5.dp))
                )

                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                Column(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = food.name,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer(modifier = Modifier.height(spacing.spaceSmall))

                    Text(
                        text = stringResource(id = R.string.kcal_per_100g, food.caloriesPer100g),
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }

            Row {
                NutrientInfo(
                    name = stringResource(id = R.string.carbs),
                    unit = stringResource(id = R.string.grams),
                    amount = food.carbsPer100g,
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium,
                )

                Spacer(modifier = Modifier.width(spacing.spaceSmall))

                NutrientInfo(
                    name = stringResource(id = R.string.protein),
                    unit = stringResource(id = R.string.grams),
                    amount = food.proteinPer100g,
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium,
                )

                Spacer(modifier = Modifier.width(spacing.spaceSmall))

                NutrientInfo(
                    name = stringResource(id = R.string.fat),
                    unit = stringResource(id = R.string.grams),
                    amount = food.fatPer100g,
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        AnimatedVisibility(visible = trackableFoodUiState.isExpanded) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceMedium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row {
                    OutlinedTextField(
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent,
                            errorBorderColor = Color.Transparent
                        ),
                        value = trackableFoodUiState.amount,
                        onValueChange = onAmountChange,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = if (trackableFoodUiState.amount.isNotBlank()) {
                                ImeAction.Done
                            } else {
                                ImeAction.Default
                            }
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                onTrack()
                                defaultKeyboardAction(ImeAction.Done)
                            }
                        ),
                        singleLine = true,
                        trailingIcon = {
                            Text(
                                text = stringResource(id = R.string.grams),
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.alignBy(LastBaseline)
                            )
                        },
                        modifier = Modifier
                            .widthIn(50.dp)
                            .padding(end = spacing.spaceExtraSmall)
                            .borderLineBottom(
                                MaterialTheme.colorScheme.onSurface
                            )
                            .alignBy(LastBaseline)
                    )
                }

                IconButton(
                    onClick = onTrack,
                    enabled = trackableFoodUiState.amount.isNotBlank(),
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(id = R.string.track),
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackableFoodItemLightPreview() {
    BaseLightPreview(modifier = Modifier.padding(16.dp)) {
        TrackableFoodItem(
            trackableFoodUiState = TrackableFoodUiState(
                amount = "100",
                isExpanded = true,
                food = TrackableFood(
                    name = "Burger",
                    imageUrl = null,
                    caloriesPer100g = 100,
                    carbsPer100g = 10,
                    fatPer100g = 10,
                    proteinPer100g = 10,
                )
            ),
            onClick = {},
            onAmountChange = {},
            onTrack = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TrackableFoodItemDarkPreview() {
    BaseDarkPreview(modifier = Modifier.padding(16.dp)) {
        TrackableFoodItem(
            trackableFoodUiState = TrackableFoodUiState(
                amount = "100",
                isExpanded = true,
                food = TrackableFood(
                    name = "Burger",
                    imageUrl = null,
                    caloriesPer100g = 100,
                    carbsPer100g = 10,
                    fatPer100g = 10,
                    proteinPer100g = 10,
                )
            ),
            onClick = {},
            onAmountChange = {},
            onTrack = {},
        )
    }
}