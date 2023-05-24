package com.h_fahmy.tracker_presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.h_fahmy.calorytracker.core.R
import com.h_fahmy.core_ui.LocalSpacing
import com.h_fahmy.core_ui.UiEventHandler
import com.h_fahmy.core_ui.util.BaseLightPreview
import com.h_fahmy.tracker_domain.model.MealType
import com.h_fahmy.tracker_domain.model.TrackableFood
import com.h_fahmy.tracker_presentation.search.components.SearchTextField
import com.h_fahmy.tracker_presentation.search.components.TrackableFoodItem
import java.time.LocalDate

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
        isHintVisible = state.isHintVisible,
        trackableFood = state.trackableFood,
        isSearching = state.isSearching,
        isNoResult = state.trackableFood.isEmpty(),
        onSearchTextChange = { viewModel.onEvent(SearchEvent.OnQueryChange(it)) },
        onSearch = {
            keyboardController?.hide()
            viewModel.onEvent(SearchEvent.OnSearch)
        },
        onSearchFocusChange = { viewModel.onEvent(SearchEvent.OnSearchFocusChange(it)) },
        onClick = { viewModel.onEvent(SearchEvent.OnToggleTrackableFood(it)) },
        onAmountChange = { (trackableFood, amount) ->
            viewModel.onEvent(
                SearchEvent.OnAmountForFoodChange(
                    food = trackableFood,
                    amount = amount
                )
            )
        },
        onTrack = {
            keyboardController?.hide()
            viewModel.onEvent(
                SearchEvent.OnTrackFoodClick(
                    food = it,
                    mealType = MealType.valueOf(mealName),
                    date = LocalDate.of(year, month, dayOfMonth)
                )
            )
        }
    )
}

@Composable
fun SearchScreenContent(
    mealName: String,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearch: () -> Unit,
    onSearchFocusChange: (Boolean) -> Unit,
    onTrack: (TrackableFood) -> Unit,
    onAmountChange: (Pair<TrackableFood, String>) -> Unit,
    onClick: (TrackableFood) -> Unit,
    trackableFood: List<TrackableFoodUiState>,
    isSearching: Boolean,
    isNoResult: Boolean,
    isHintVisible: Boolean,
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
            shouldShowHint = isHintVisible,
            onValueChange = onSearchTextChange,
            onSearch = onSearch,
            onFocusChange = { onSearchFocusChange(it.isFocused) }
        )

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(trackableFood) { food ->
                TrackableFoodItem(
                    trackableFoodUiState = food,
                    onClick = { onClick(food.food) },
                    onAmountChange = { onAmountChange(food.food to it) },
                    onTrack = { onTrack(food.food) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when {
            isSearching -> CircularProgressIndicator()
            isNoResult -> Text(
                text = stringResource(id = R.string.no_results),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenContentPreview() {
    BaseLightPreview {
        SearchScreenContent(
            mealName = "Breakfast",
            searchText = "",
            onSearchTextChange = {},
            onSearch = {},
            onSearchFocusChange = {},
            onTrack = {},
            onAmountChange = {},
            onClick = {},
            trackableFood = listOf(
                TrackableFoodUiState(
                    food = TrackableFood(
                        name = "Apple",
                        imageUrl = null,
                        caloriesPer100g = 52,
                        carbsPer100g = 14,
                        fatPer100g = 0,
                        proteinPer100g = 0,
                    )
                ),
                TrackableFoodUiState(
                    food = TrackableFood(
                        name = "Banana",
                        imageUrl = null,
                        caloriesPer100g = 89,
                        carbsPer100g = 23,
                        fatPer100g = 0,
                        proteinPer100g = 0,
                    )
                ),
                TrackableFoodUiState(
                    food = TrackableFood(
                        name = "Orange",
                        imageUrl = null,
                        caloriesPer100g = 47,
                        carbsPer100g = 12,
                        fatPer100g = 0,
                        proteinPer100g = 0,
                    )
                ),
            ),
            isSearching = false,
            isNoResult = false,
            isHintVisible = true,
        )
    }
}
