package com.h_fahmy.profile_domain.di

import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.profile_domain.use_case.LoadUserProfile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ProfileDomainModule {

    @Provides
    @ViewModelScoped
    fun provideUserProfileLoader(
        preferences: Preferences
    ) = LoadUserProfile(preferences = preferences)
}