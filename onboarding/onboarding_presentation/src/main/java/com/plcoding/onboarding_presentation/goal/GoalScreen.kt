package com.plcoding.onboarding_presentation.goal

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
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.util.UiEvent
import com.plcoding.onboarding_presentation.R
import com.plcoding.onboarding_presentation.components.ActionButton
import com.plcoding.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect

@Composable
fun GoalScreen(
    onNextClick: () -> Unit, //callback to the ui event in viewModel
    viewModel: GoalViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    //collect the viewModel events
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect { event -> //observes the uiEvents
            when(event){
                is UiEvent.Success -> onNextClick()
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
                text = stringResource(id = R.string.your_goal),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton( //male button
                    text = stringResource(id = R.string.lose),
                    isSelected = viewModel.selectedGoalType is GoalType.LoseWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onGoalTypeClick(GoalType.LoseWeight)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    ),
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton( //female button
                    text = stringResource(id = R.string.keep),
                    isSelected = viewModel.selectedGoalType is GoalType.KeepWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onGoalTypeClick(GoalType.KeepWeight)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    ),
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton( //female button
                    text = stringResource(id = R.string.gain),
                    isSelected = viewModel.selectedGoalType is GoalType.GainWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onGoalTypeClick(GoalType.GainWeight)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    ),
                )
            }
        }
        ActionButton( //next button in the bottom right
            text = stringResource(id = R.string.next),
            onClick = viewModel::onNextClick,// :: directly calls the named function
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}