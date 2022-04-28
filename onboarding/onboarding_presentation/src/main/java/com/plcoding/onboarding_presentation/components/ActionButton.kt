package com.plcoding.onboarding_presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.core_ui.LocalSpacing


@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit, //handle the click event outside
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true, //handle this outside
    textStyle: TextStyle = MaterialTheme.typography.button
) {
    Button(
        onClick = onClick, //callback to the function outside of this composable
        enabled = isEnabled,
        modifier = modifier,
        shape = RoundedCornerShape(100.dp) //rounds the button out Oval
    ) {
        Text(
            text = text,
            style = textStyle,
            color = MaterialTheme.colors.onPrimary, //green on white
            modifier = Modifier.padding(LocalSpacing.current.spaceSmall) //8.dp
        )
    }
}