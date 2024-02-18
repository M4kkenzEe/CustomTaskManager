package com.example.testmobapp.presentation.newview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardTaskScreen(
    modifier: Modifier = Modifier,
    taskTitle: String = "task title",
    taskDesc: String = "task desc"
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .border(BorderStroke(width = 1.dp, color = Color.Black))
            .height(90.dp)
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
            Text(
                text = taskTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                lineHeight = 12.1.sp,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
@Preview
fun CardTaskScreenPrev() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(104.dp)
    ) {
        CardTaskScreen()
        CardTaskScreen()
        CardTaskScreen()
        CardTaskScreen()
    }

}