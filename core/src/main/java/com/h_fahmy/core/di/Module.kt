package com.h_fahmy.core.di

import com.h_fahmy.core.domain.use_case.FilterOutDigits
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
    fun provideFilterOutDigitsUseCase() = FilterOutDigits()
}