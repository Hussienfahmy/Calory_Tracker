package com.h_fahmy.core.di

import android.content.Context
import com.h_fahmy.core.data.preferences.UserInfoDataStore
import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.core.domain.use_case.FilterOutDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object Module {


    @Provides
    @ViewModelScoped
    fun provideFilterOutDigitsUseCase() = FilterOutDigits()

    @Provides
    @ViewModelScoped
    fun providePresences(@ApplicationContext context: Context): Preferences =
        UserInfoDataStore.create(context)
}