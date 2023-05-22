package com.h_fahmy.core_ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.h_fahmy.core_ui.util.UiEvent
import kotlinx.coroutines.flow.Flow

/**
 * base composable for all screens that use UiEvent
 */
@Composable
fun UiEventHandler(
    key: Any? = Unit,
    onNavigate: (UiEvent.Navigate) -> Unit,
    uiEvent: Flow<UiEvent>,
    snackBarHostState: SnackbarHostState? = null,
    onShowSnackBar: () -> Unit = {},
    onNavigateUp: () -> Unit = {},
) {
    val context = LocalContext.current

    LaunchedEffect(key) {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState?.showSnackbar(
                        message = event.message.asString(context = context)
                    )
                    onShowSnackBar()
                }

                is UiEvent.NavigateUp -> onNavigateUp()
            }
        }
    }
}