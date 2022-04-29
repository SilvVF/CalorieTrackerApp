package com.plcoding.onboarding_presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class GenderViewModel @Inject constructor(
    private val preferences : Preferences //default pref is the implementation
): ViewModel(){
    //keeps track of the gender being highlighted
    var selectedGender by mutableStateOf<Gender>(Gender.Male)
        private set //allows the value to only be changed from within the viewModel

    //channel used to send one time events to the UI - Navigate, show snack bar
    //viewModel version that can send events
    private val _uiEvent = Channel<UiEvent>()
    //exposed ui event
    val uiEvent = _uiEvent.receiveAsFlow() //triggered once for every event

    //handle the callback from the gender buttons
    fun onGenderClick(gender: Gender) {
        selectedGender = gender
    }
    //button at the bottom of the screen
    fun onNextClick(){
        viewModelScope.launch {
            preferences.saveGender(selectedGender)
            _uiEvent.send(UiEvent.Navigate(Route.AGE)) //navigate to next screen
        }
    }
}