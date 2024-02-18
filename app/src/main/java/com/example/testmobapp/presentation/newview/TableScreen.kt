package com.example.testmobapp.presentation.newview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TableScreen() {

}

@Composable
fun TaskColumn(modifier: Modifier = Modifier, label: String = "Не начатые") {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .background(Color.Black)
                .height(24.dp)
                .padding(top = 6.dp),
        )

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .border(BorderStroke(1.dp, Color.Black))) {

        }

    }
}


@Composable
@Preview
fun TableScreenPrev() {
    Row(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp).padding(top = 42.dp, bottom = 36.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        TaskColumn(modifier = Modifier.weight(1f))
        TaskColumn(modifier = Modifier.weight(1f), label = "В работе")
        TaskColumn(modifier = Modifier.weight(1f), label = "Завершенные")
    }

}