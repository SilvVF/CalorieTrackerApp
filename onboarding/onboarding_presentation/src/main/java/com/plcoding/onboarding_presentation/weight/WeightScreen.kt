package com.plcoding.onboarding_presentation.weight

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.LocalSpacing
import com.plcoding.core.util.UiEvent
import com.plcoding.onboarding_presentation.R
import com.plcoding.onboarding_presentation.components.ActionButton
import com.plcoding.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collect

@Composable
fun WeightScreen(
    scaffoldState: ScaffoldState,
    onNextClick: () -> Unit,
    viewModel: WeightViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    //collect the viewModel events
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect { event -> //observes the uiEvents
            when(event){
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackBar -> { // need scaffold in main to wrap
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
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
                text = stringResource(id = R.string.whats_your_weight),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = viewModel.weight,
                onValueChanged = viewModel::onWeightEnter, //same as { vieModel.onAgeEnter(it) }
                unit = stringResource(id = R.string.kg)
            )
        }
        ActionButton( //next button in the bottom right
            text = stringResource(id = R.string.next),
            onClick = viewModel::onNextClick,// :: directly calls the named function
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}