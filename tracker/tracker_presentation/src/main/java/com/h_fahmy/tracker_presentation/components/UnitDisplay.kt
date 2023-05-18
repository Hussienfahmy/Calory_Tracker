package com.h_fahmy.tracker_presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.util.BaseLightPreview

@Composable
fun UnitDisplay(
    modifier: Modifier = Modifier,
    unit: String,
    amount: Int,
    textColor: Color,
    amountTextSize: TextUnit = 20.sp,
    unitTextSize: TextUnit = 14.sp,
) {
    val spacing = LocalSpacing.current

    Row(modifier = modifier) {
        Text(
            text = amount.toString(),
            style = MaterialTheme.typography.displayLarge,
            color = textColor,
            fontSize = amountTextSize,
            modifier = Modifier.alignBy(LastBaseline)
        )

        Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))

        Text(
            text = unit,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = unitTextSize,
            color = textColor,
            modifier = Modifier.alignBy(LastBaseline)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitDisplayPreview() {
    BaseLightPreview(modifier = Modifier.padding(10.dp)) {
        UnitDisplay(
            unit = "km",
            amount = 10,
            amountTextSize = 20.sp,
            unitTextSize = 14.sp,
            textColor = MaterialTheme.colorScheme.tertiary
        )
    }
}