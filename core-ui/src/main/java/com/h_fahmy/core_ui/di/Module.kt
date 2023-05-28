package com.h_fahmy.core_ui.di

import com.h_fahmy.core_ui.domain.use_case.ValidateNutrients
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
    fun provideValidateNutrientUseCase() = ValidateNutrients()
}