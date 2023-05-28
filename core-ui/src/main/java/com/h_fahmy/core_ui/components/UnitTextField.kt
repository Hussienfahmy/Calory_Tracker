package com.h_fahmy.core_ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.h_fahmy.core_ui.util.BaseLightPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    unit: String,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colorScheme.primary,
        fontSize = 70.sp,
        textAlign = TextAlign.Center
    ),
    error: Boolean = false
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent
            ),
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            isError = error,
            modifier = Modifier
                .alignBy(LastBaseline),
            trailingIcon = {
                Text(
                    text = unit,
                    modifier = Modifier.alignBy(LastBaseline)
                )
            }
        )
    }
}

@Preview
@Composable
fun UnitTextPreview() {
    BaseLightPreview(
        modifier = Modifier.padding(16.dp)
    ) {
        UnitTextField(
            value = "100",
            onValueChange = {},
            unit = "cm"
        )
    }
}