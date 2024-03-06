package com.example.testmobapp.presentation.newview.references

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmobapp.presentation.ui.theme.Gray80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskTitleTF(
    value: String,
    onValueChange: (s: String) -> Unit = {},
    modifier: Modifier = Modifier,
    textHint: String = "Название"
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedTextColor = Color.Black,
        ),
        placeholder = {
            Text(
                text = textHint,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Gray80,
                lineHeight = 16.94.sp
            )
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            lineHeight = 16.94.sp
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDescriptionTF(
    value: String,
    onValueChange: (s: String) -> Unit = {},
    modifier: Modifier = Modifier,
    textHint: String = "Описание"
) {

    var offset by remember {
        mutableFloatStateOf(0f)
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .padding(4.dp)
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollableState { delta ->
                    offset += delta
                    delta
                }
            ),
        maxLines = 40,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            cursorColor = Color.Black,
            focusedTextColor = Color.Black,
        ),

        placeholder = {
            Text(
                text = textHint,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Gray80,
                lineHeight = 16.94.sp
            )
        },
        textStyle = TextStyle(
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            lineHeight = 16.94.sp
        ),
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
@Preview
fun TaskTitlePrev() {

    var tfState by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TaskTitleTF(
            value = tfState,
            onValueChange = { tfState = it }
        )

        TaskDescriptionTF(
            value = tfState,
            onValueChange = { tfState = it }
        )
    }


}