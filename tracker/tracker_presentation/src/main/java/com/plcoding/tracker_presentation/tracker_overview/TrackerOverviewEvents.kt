package com.plcoding.tracker_presentation.tracker_overview

import com.plcoding.tracker_domain.model.TrackedFood

sealed class TrackerOverviewEvents {
    object OnNextDayClick: TrackerOverviewEvents()
    object OnPreviousDayClick: TrackerOverviewEvents()
    data class OnToggleMealClick(val meal: Meal): TrackerOverviewEvents()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood): TrackerOverviewEvents()
    data class OnAddFoodClick(val meal: Meal): TrackerOverviewEvents()
}
