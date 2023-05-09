package com.h_fahmy.core_ui.viewmodel

import androidx.lifecycle.ViewModel
import com.h_fahmy.core_ui.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * base view model have ui event code which will be used in all view models that need ui event
 */
open class UiEventViewModel : ViewModel() {

    protected val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
}