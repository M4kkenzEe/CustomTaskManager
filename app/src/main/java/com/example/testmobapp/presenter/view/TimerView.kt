package com.example.testmobapp.presenter.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testmobapp.presenter.viewmodel.TimerViewModel

@Composable
fun TimerView(vm: TimerViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable { vm.showButtonsState.value = !vm.showButtonsState.value },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = formatTime(vm.timeState.value),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 45.sp
        )

        if (vm.showButtonsState.value) {

            Button(
                onClick = { vm.pressStartButton() },
                modifier = Modifier.padding(top = 28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            ) {
                Text(text = "Старт", color = Color.Black)
            }

            Button(
                onClick = { vm.pressPauseButton() },
                modifier = Modifier.padding(top = 28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            ) {
                Text(text = "Pause", color = Color.Black)
            }

            Button(
                onClick = { vm.pressUnpauseButton() },
                modifier = Modifier.padding(top = 28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            ) {
                Text(text = "Unpause", color = Color.Black)
            }

            Button(
                onClick = { vm.pressDropButton() },
                modifier = Modifier.padding(top = 28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            ) {
                Text(text = "Drop", color = Color.Black)
            }
        }
    }

}

fun formatTime(seconds: Int): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60
    return String.format("%d:%d:%d", hours, minutes, remainingSeconds)
}

@Composable
@Preview
fun TimerViewPrev() {
    TimerView()
}