package com.h_fahmy.onboarding_presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.h_fahmy.core.navigation.Route
import com.h_fahmy.core.util.UiEvent
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.onboarding_presentation.components.ActionButton
import com.h_fahmy.calorytracker.core.R as CoreR

@Composable
fun WelcomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = CoreR.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayMedium,
        )

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        ActionButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = CoreR.string.next),
            onClick = { onNavigate(UiEvent.Navigate(Route.AGE)) },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(
        onNavigate = {}
    )
}