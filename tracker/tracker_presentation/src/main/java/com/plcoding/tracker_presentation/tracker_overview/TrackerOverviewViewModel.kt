package com.plcoding.tracker_presentation.tracker_overview


import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.core.data.preferences.Preferences
import com.plcoding.core.util.UiEvent
import com.plcoding.tracker_domain.repository.use_cases.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    preferences: Preferences,
    private val trackerUseCases: TrackerUseCases,
): ViewModel() {

    var state by mutableStateOf(TrackerOverviewState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getFoodsForDateJob: Job? = null

    init { //only gets preferences on first launch
        refreshFoods()
        preferences.saveShouldShowOnboarding(false)
    }

    fun onEvent(event: TrackerOverviewEvent) {
        when (event) {
            is TrackerOverviewEvent.OnDeleteTrackedFoodClick -> {
                viewModelScope.launch {
                    trackerUseCases.deleteTrackedFood(event.trackedFood)
                    refreshFoods()
                }
            }
            is TrackerOverviewEvent.OnNextDayClick -> {
                state = state.copy(
                    date = state.date.plusDays(1)
                )
                refreshFoods()
            }
            is TrackerOverviewEvent.OnPreviousDayClick -> {
                state = state.copy(
                    date = state.date.minusDays(1)
                )
                refreshFoods()
            }
            is TrackerOverviewEvent.OnToggleMealClick -> {
                state = state.copy(
                    meals = state.meals.map {
                        if(it.name == event.meal.name) {
                            it.copy(isExpanded = !it.isExpanded)
                        } else it
                    }
                )
            }
        }
    }
    private fun refreshFoods() {
        getFoodsForDateJob?.cancel()
        getFoodsForDateJob = trackerUseCases
            .getFoodsForDate(state.date) //get all new foods
            .onEach { foods ->  //update ui state with new foods
                val nutrientsResult = trackerUseCases.calculateMealNutrients(foods)
                state = state.copy(
                    totalCarbs = nutrientsResult.totalCarbs,
                    totalFat = nutrientsResult.totalFat,
                    totalProtein = nutrientsResult.totalProtein,
                    carbsGoal = nutrientsResult.carbsGoal,
                    proteinGoal = nutrientsResult.proteinGoal,
                    fatGoal = nutrientsResult.fatGoal,
                    trackedFoods = foods,
                    totalCalories = nutrientsResult.totalCalories,
                    caloriesGoal = nutrientsResult.caloriesGoal,
                    meals = state.meals.map { //updating the map of all of the meal nutrients
                        val nutrientsForMeal =
                            nutrientsResult.mealNutrients[it.mealType]
                                ?: return@map it.copy(
                                    carbs = 0,
                                    protein = 0,
                                    fat = 0,
                                    calories = 0
                                )
                        it.copy(
                            carbs = nutrientsForMeal.carbs,
                            protein = nutrientsForMeal.protein,
                            fat = nutrientsForMeal.fat,
                            calories = nutrientsForMeal.calories
                        )
                    }
                )
            }.launchIn(viewModelScope)
    }
}