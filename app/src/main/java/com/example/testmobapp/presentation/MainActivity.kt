package com.example.testmobapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.testmobapp.presentation.navigation.MainScreen
import com.example.testmobapp.presentation.ui.theme.TestMobAppTheme

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
