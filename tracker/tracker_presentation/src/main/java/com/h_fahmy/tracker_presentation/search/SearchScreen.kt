package com.h_fahmy.tracker_presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.UiEventHandler
import com.h_fahmy.core_ui.util.BaseLightPreview
import com.h_fahmy.tracker_presentation.search.components.SearchTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    onNavigateUp: () -> Unit,
    mealName: String,
    dayOfMonth: Int,
    month: Int,
    year: Int,
    snackBarHostState: SnackbarHostState,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val state = viewModel.state

    UiEventHandler(
        onNavigate = {},
        uiEvent = viewModel.uiEvent,
        key = keyboardController,
        snackBarHostState = snackBarHostState,
        onShowSnackBar = { keyboardController?.hide() },
        onNavigateUp = onNavigateUp,
    )

    SearchScreenContent(
        mealName = mealName,
        searchText = state.query,
        onSearchTextChange = { viewModel.onEvent(SearchEvent.OnQueryChange(it)) },
        onSearch = { viewModel.onEvent(SearchEvent.OnSearch) },
        onSearchFocusChange = { viewModel.onEvent(SearchEvent.OnSearchFocusChange(it)) },
    )
}

@Composable
fun SearchScreenContent(
    mealName: String,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearch: () -> Unit,
    onSearchFocusChange: (Boolean) -> Unit,
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium)
    ) {
        Text(
            text = stringResource(id = R.string.add_meal, mealName),
            style = MaterialTheme.typography.displayMedium,
        )

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        SearchTextField(
            text = searchText,
            onValueChange = onSearchTextChange,
            onSearch = onSearch,
            onFocusChange = { onSearchFocusChange(it.isFocused) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenContentPreview() {
    BaseLightPreview(modifier = Modifier.padding(16.dp)) {
        SearchScreenContent(
            mealName = "Breakfast",
            searchText = "",
            onSearchTextChange = {},
            onSearch = {},
            onSearchFocusChange = {},
        )
    }
}
