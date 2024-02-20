package com.example.testmobapp.presentation.newview.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmobapp.R

@Composable
fun AddFAB(onClick: () -> Unit = {}) {
    SmallFloatingActionButton(
        onClick = { onClick() },
        containerColor = Color.Black,
        contentColor = Color.White
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null,
            modifier = Modifier.width(72.dp)
        )
    }
}

@Composable
@Preview
fun ButtonsPrev() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        AddFAB()
    }

}