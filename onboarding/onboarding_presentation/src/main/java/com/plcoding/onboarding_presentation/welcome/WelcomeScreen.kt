package com.plcoding.onboarding_presentation.welcome


import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core_ui.LocalSpacing
import com.plcoding.core.R
import com.plcoding.core.navigation.Route
import com.plcoding.core.util.UiEvent
import com.plcoding.onboarding_presentation.components.ActionButton

@Composable
fun WelcomeScreen(
    //navigation composable will receive events from callback
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    //gets the spacing from the coreUI package
    //core ui is included by default in the compose module Gradle build file
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium), //fill screen
        verticalArrangement = Arrangement.Center, //center of height
        horizontalAlignment = Alignment.CenterHorizontally //middle of width
    ) {
        Text(
            //add string res to core module that can be shared between all modules
            text = stringResource(id = R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1
        )
        //constantly have fixed spacing values can be part of the theme instead
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = { //handle the callback
                onNavigate(UiEvent.Navigate(Route.GENDER))
            },
            modifier = Modifier.align(Alignment.CenterHorizontally) //center in width
        )
    }

}