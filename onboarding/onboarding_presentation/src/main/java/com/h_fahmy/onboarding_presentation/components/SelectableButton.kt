package com.h_fahmy.onboarding_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.h_fahmy.core_ui.LocalSpacing

@Composable
fun SelectableButton(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    color: Color,
    selectedTextColor: Color,
    onClick: () -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .border(
                width = 2.dp,
                color = color,
                shape = RoundedCornerShape(100.dp)
            )
            .background(
                color = if (selected) selectedTextColor else Color.Transparent,
                shape = RoundedCornerShape(100.dp)
            )
            .clickable(onClick = onClick)
            .padding(LocalSpacing.current.spaceMedium)
    ) {
        Text(
            text = text,
            style = textStyle,
            color = if (selected) color else selectedTextColor,
        )
    }
}

@Preview
@Composable
fun SelectableButtonSelectedPreview() {
    Surface(
        modifier = Modifier.padding(16.dp)
    ) {
        SelectableButton(
            text = "Button",
            selected = true,
            color = MaterialTheme.colorScheme.onPrimary,
            selectedTextColor = MaterialTheme.colorScheme.secondary,
            onClick = { })
    }
}


@Preview
@Composable
fun SelectableButtonNotSelectedPreview() {
    Surface(
        modifier = Modifier.padding(16.dp)
    ) {
        SelectableButton(
            text = "Button",
            selected = false,
            color = MaterialTheme.colorScheme.onPrimary,
            selectedTextColor = MaterialTheme.colorScheme.secondary,
            onClick = { })
    }
}