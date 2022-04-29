package com.plcoding.onboarding_presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.core_ui.LocalSpacing

@Composable
fun UnitTextField( //big green text with small unit next to it
    value: String,
    onValueChanged: (String) -> Unit, //call back with changed text value
    unit: String, //unit of measure to be displayed bottom right of text
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.primaryVariant, //dark green
        fontSize = 70.sp,
    ),
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
       BasicTextField(
           value = value,
           onValueChange = onValueChanged,
           textStyle = textStyle,
           keyboardOptions = KeyboardOptions( //makes values come from a numpad
               keyboardType = KeyboardType.Number
           ),
           singleLine = true,
           modifier = Modifier  //works similar to wrap content
               .width(IntrinsicSize.Min) //makes the text field only occupy what it needs
               .alignBy(LastBaseline) //aligns the big and little text to the bottom sticky to bottom
       )
        Spacer(modifier = Modifier.width(spacing.spaceSmall))
        Text(
            text = unit,
            modifier = Modifier.alignBy(LastBaseline) //needs to be applied to both texts
        )
    }
}