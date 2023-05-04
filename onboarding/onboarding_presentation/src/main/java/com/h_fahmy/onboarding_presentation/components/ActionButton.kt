package com.h_fahmy.onboarding_presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.h_fahmy.core_ui.LocalSpacing

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    textStyles: TextStyle = MaterialTheme.typography.labelLarge,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(100.dp),
    ) {
        Text(
            text = text,
            style = textStyles,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(LocalSpacing.current.spaceSmall)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ActionButtonPreview() {
    Surface(
        modifier = Modifier.padding(16.dp)
    ) {
        ActionButton(text = "Next", onClick = { })
    }
}