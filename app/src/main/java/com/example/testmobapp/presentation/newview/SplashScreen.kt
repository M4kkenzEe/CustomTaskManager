package com.example.testmobapp.presentation.newview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.testmobapp.R
import com.example.testmobapp.ui.theme.WhiteSoft

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "timeon", fontSize = 64.sp,
//            fontWeight = FontWeight(600),
            color = WhiteSoft,
            fontFamily = FontFamily(Font(R.font.rg_standart_semibold))
        )
    }
}

@Composable
@Preview
fun SplashScreenPrev() {
    SplashScreen()
}