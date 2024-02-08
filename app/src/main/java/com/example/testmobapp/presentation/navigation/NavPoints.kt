package com.example.testmobapp.presentation.navigation

sealed class NavPoints(val name: String, val route: String) {
    object TimerScreen : NavPoints(name = "TimerScreen", route = "timer_route")
    object TableScreen : NavPoints(name = "TableScreen", route = "table_screen")
}