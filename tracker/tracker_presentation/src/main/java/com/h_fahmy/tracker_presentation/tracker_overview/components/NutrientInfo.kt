package com.h_fahmy.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.h_fahmy.core_ui.util.BaseLightPreview
import com.h_fahmy.tracker_presentation.components.UnitDisplay

@Composable
fun NutrientInfo(
    modifier: Modifier = Modifier,
    name: String,
    unit: String,
    amount: Int,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    amountTextSize: TextUnit = 20.sp,
    unitTextSize: TextUnit = 14.sp,
    nameTextStyle: TextStyle = MaterialTheme.typography.bodyLarge
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UnitDisplay(
            unit = unit,
            amount = amount,
            amountTextSize = amountTextSize,
            textColor = textColor,
            unitTextSize = unitTextSize,
        )

        Text(text = name, style = nameTextStyle, color = MaterialTheme.colorScheme.onBackground)
    }
}

@Preview(showBackground = true)
@Composable
fun NutrientInfoPreview() {
    BaseLightPreview(modifier = Modifier.padding(10.dp)) {
        NutrientInfo(
            name = "Carbs",
            unit = "g",
            amount = 109,
            amountTextSize = 20.sp,
            unitTextSize = 14.sp,
            textColor = MaterialTheme.colorScheme.onBackground
        )
    }
}