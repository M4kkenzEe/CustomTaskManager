package com.example.testmobapp.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.testmobapp.presentation.ui.theme.Gray63

@Composable
fun DropDownMenuScreen(
    expanded: Boolean = true,
    onDismissRequest: () -> Unit = {},
    onButtonClick: () -> Unit = {}
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .wrapContentHeight()
            .background(Gray63)
            .width(76.dp),
        offset = DpOffset(100.dp, 100.dp),
    ) {

    }
}


@Composable
@Preview
fun DropDownMenuScreenPrev() {
    DropDownMenuScreen()
}