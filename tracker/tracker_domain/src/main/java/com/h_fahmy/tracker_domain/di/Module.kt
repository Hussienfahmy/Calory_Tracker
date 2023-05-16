package com.h_fahmy.tracker_domain.di

import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.tracker_domain.repository.TrackerRepository
import com.h_fahmy.tracker_domain.use_case.CalculateMealNutrients
import com.h_fahmy.tracker_domain.use_case.DeleteTrackedFood
import com.h_fahmy.tracker_domain.use_case.GetFoodsByDate
import com.h_fahmy.tracker_domain.use_case.SearchFood
import com.h_fahmy.tracker_domain.use_case.TrackFood
import com.h_fahmy.tracker_domain.use_case.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object Module {

    @Provides
    @ViewModelScoped
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            getFoodsByDate = GetFoodsByDate(repository),
            deleteTrackedFood = DeleteTrackedFood(repository),
            calculateMealNutrients = CalculateMealNutrients(preferences),
        )
    }
}