package com.plcoding.onboarding_presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.data.preferences.Preferences
import com.plcoding.core.domain.use_case.FilterOutDigits
import com.plcoding.core.navigation.Route
import com.plcoding.core.util.UiEvent
import com.plcoding.core.util.UiText
import com.plcoding.onboarding_presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences, //preferences from app module in app package
    private val filterOutDigits: FilterOutDigits
): ViewModel() {

    var height by mutableStateOf(
        if (preferences.loadUserInfo().gender is Gender.Male)"180"
        else "160"
    )
        private set //allows only the viewModel to change this value

    //channel used to send one time events to the UI - Navigate, show snack bar
    //viewModel version that can send events
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow() //triggered once for every event exposed

    fun onHeightEnter(height: String) { //needs a use case to filter height found in core domain
         if (height.length <= 3) this.height = filterOutDigits(height) // == .invoke(height)
    }

    fun onNextClick(){
        viewModelScope.launch {
            val heightNumber = height.toIntOrNull() ?: kotlin.run { //UI Text
                _uiEvent.send( //want to use string resources but need context -> create helper class
                    UiEvent.ShowSnackBar(
                        UiText.StringResource(R.string.error_height_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveHeight(heightNumber)
            _uiEvent.send(UiEvent.Navigate(Route.WEIGHT))
        }
    }
}