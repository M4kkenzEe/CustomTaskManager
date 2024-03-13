package com.example.testmobapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.testmobapp.presentation.ui.theme.TestMobAppTheme
import com.example.testmobapp.presentation.view.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestMobAppTheme {
                MainScreen()
            }
        }
    }
}
