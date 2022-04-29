package com.plcoding.calorytracker.navigation

import androidx.navigation.NavController
import com.plcoding.core.util.UiEvent

//allows the callback from ui screens to be able to navigate to the next composable
//from the outside
fun NavController.navigate(event: UiEvent.Navigate){
    this.navigate(event.route)
}