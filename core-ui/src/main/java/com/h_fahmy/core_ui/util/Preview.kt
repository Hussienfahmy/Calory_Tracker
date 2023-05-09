package com.h_fahmy.core_ui.util

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.h_fahmy.core_ui.theme.CaloryTrackerTheme

@Composable
fun BaseLightPreview(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    CaloryTrackerTheme(darkTheme = false, dynamicColor = false) {
        Surface(content = content, modifier = modifier)
    }
}

@Composable
fun BaseDarkPreview(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    CaloryTrackerTheme(darkTheme = true, dynamicColor = false) {
        Surface(content = content, modifier = modifier)
    }
}