package com.plcoding.core.util

//define events to send from view models to composable
sealed class UiEvent {
    //navigate to route using  this
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp: UiEvent()
}
