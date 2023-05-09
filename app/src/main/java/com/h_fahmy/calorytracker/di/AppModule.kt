package com.h_fahmy.calorytracker.di

import android.content.Context
import com.h_fahmy.core.data.preferences.UserInfoDataStore
import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.core.domain.use_case.FilterOutDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providePresences(@ApplicationContext context: Context): Preferences =
        UserInfoDataStore.create(context)

    @Provides
    @Singleton
    fun provideFilterOutDigitsUseCase() = FilterOutDigits()
}