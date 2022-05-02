package com.plcoding.on_boarding_domain.di

import com.plcoding.on_boarding_domain.use_cases.ValidateNutrients
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingDomainModule {


    @Provides
    @ViewModelScoped
    fun provideValidateNutrientsUseCase(): ValidateNutrients {
        return ValidateNutrients()
    }
}