package com.h_fahmy.calorytracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core_ui.util.BaseLightPreview

@Composable
fun AppBottomNavigation(
    currentDestination: String,
    onTrackerOverviewClick: () -> Unit,
    onProfileClick: () -> Unit,
) {
    BottomAppBar {
        // TrackerOverview Screen
        NavigationBarItem(
            selected = currentDestination == Route.TRACKER_OVERVIEW,
            onClick = onTrackerOverviewClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = stringResource(id = R.string.tracker_overview)
                )
            }
        )

        // Profile Screen
        NavigationBarItem(
            selected = currentDestination == Route.PROFILE,
            onClick = onProfileClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(id = R.string.profile)
                )
            }
        )
    }
}

@Preview
@Composable
fun AppBottomNavigationPreview() {
    BaseLightPreview {
        AppBottomNavigation(
            currentDestination = Route.TRACKER_OVERVIEW,
            onTrackerOverviewClick = {},
            onProfileClick = {}
        )
    }
}