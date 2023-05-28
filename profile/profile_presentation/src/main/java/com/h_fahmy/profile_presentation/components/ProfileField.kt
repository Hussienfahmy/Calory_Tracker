package com.h_fahmy.profile_presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.components.UnitTextField
import com.h_fahmy.core_ui.util.BaseLightPreview

@Composable
fun ProfileField(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit,
) {
    val spacing = LocalSpacing.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacing.spaceSmall),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.primary
        )

        content()
    }
}

@Composable
fun ProfileTextField(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    unit: String,
    onValueChanged: (String) -> Unit,
    error: Boolean = false,
) {
    ProfileField(title = title, modifier = modifier) {
        UnitTextField(
            value = value,
            onValueChange = onValueChanged,
            unit = unit,
            modifier = Modifier.widthIn(max = 150.dp),
            textStyle = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.End),
            error = error
        )
    }
}

@Preview
@Composable
fun ProfileTextFieldPreview() {
    BaseLightPreview {
        ProfileTextField(title = "Age", value = "24", onValueChanged = {}, unit = "years")
    }
}