package com.plcoding.onboarding_presentation.gender

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.LocalSpacing
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.util.UiEvent
import com.plcoding.onboarding_presentation.R
import com.plcoding.onboarding_presentation.components.ActionButton
import com.plcoding.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect

@Composable
fun GenderScreen(
    onNavigate: (UiEvent.Navigate) -> Unit, //callback to the ui event in viewModel
    genderViewModel: GenderViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    //collect the viewModel events
    LaunchedEffect(key1 = true){
        genderViewModel.uiEvent.collect { event -> //observes the uiEvents
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ){
        Column(
            modifier = Modifier.fillMaxSize(), //take up full size match parent
            verticalArrangement = Arrangement.Center, // center with height
            horizontalAlignment = Alignment.CenterHorizontally //center to width
        ) {
            Text( //question above the buttons
                text = stringResource(id = R.string.whats_your_gender),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton( //male button
                    text = stringResource(id = R.string.male),
                    isSelected = genderViewModel.selectedGender is Gender.Male,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        genderViewModel.onGenderClick(Gender.Male)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    ),
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton( //female button
                    text = stringResource(id = R.string.female),
                    isSelected = genderViewModel.selectedGender is Gender.Female,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        genderViewModel.onGenderClick(Gender.Female)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    ),
                )
            }
        }
        ActionButton( //next button in the bottom right
            text = stringResource(id = R.string.next),
            onClick = genderViewModel::onNextClick,// :: directly calls the named function
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}