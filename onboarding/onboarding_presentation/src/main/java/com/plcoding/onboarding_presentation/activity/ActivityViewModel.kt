package com.plcoding.onboarding_presentation.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.preferences.Preferences
import com.plcoding.core.navigation.Route
import com.plcoding.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel //hilt needs to be aware its a view model
class ActivityViewModel @Inject constructor(
    private val preferences : Preferences //default pref is the implementation
): ViewModel() {
    //keeps track of the gender being highlighted
    var selectedActivityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
        private set //allows the value to only be changed from within the viewModel

    //channel used to send one time events to the UI - Navigate, show snack bar
    //viewModel version that can send events
    private val _uiEvent = Channel<UiEvent>()
    //exposed ui event
    val uiEvent = _uiEvent.receiveAsFlow() //triggered once for every event

    //handle the callback from the gender buttons
    fun onActivityLevelClick(activityLevel: ActivityLevel) {
        selectedActivityLevel = activityLevel
    }
    //button at the bottom of the screen
    fun onNextClick(){
        viewModelScope.launch {
            preferences.saveActivityLevel(selectedActivityLevel)
            _uiEvent.send(UiEvent.Navigate(Route.GOAL)) //navigate to next screen
        }
    }
}