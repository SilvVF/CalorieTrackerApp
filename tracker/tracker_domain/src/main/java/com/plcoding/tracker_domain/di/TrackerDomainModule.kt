package com.plcoding.tracker_domain.di

import com.plcoding.core.domain.preferences.Preferences
import com.plcoding.tracker_domain.repository.TrackerRepository
import com.plcoding.tracker_domain.repository.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases{
        return TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            getFoodsForDate = GetFoodsForDate(repository),
            calculateMealNutrients = CalculateMealNutrients(preferences),
            deleteTrackedFood = DeleteTrackedFood(repository)
        )
    }
}