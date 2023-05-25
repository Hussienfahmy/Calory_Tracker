@file:OptIn(ExperimentalMaterial3Api::class)

package com.h_fahmy.tracker_presentation.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core_ui.util.BaseLightPreview

@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    hint: String = stringResource(id = R.string.search),
    shouldShowHint: Boolean = false,
    onFocusChange: (FocusState) -> Unit = {},
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = {
            if (shouldShowHint) {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        trailingIcon = {
            IconButton(
                onClick = onSearch,
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search)
                )
            }
        },
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
                defaultKeyboardAction(ImeAction.Search)
            }
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(2.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .onFocusChanged { onFocusChange(it) },
    )
}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldPreview() {
    BaseLightPreview(modifier = Modifier.padding(16.dp)) {
        SearchTextField(
            text = "",
            onValueChange = {},
            onSearch = {},
            shouldShowHint = true,
        )
    }
}