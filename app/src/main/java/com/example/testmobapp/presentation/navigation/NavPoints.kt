package com.example.testmobapp.presentation.navigation

sealed class NavPoints(val name: String, val route: String) {
    object SplashScreen : NavPoints(name = "SplashScreen", route = "splash_route")
    object TimerScreen : NavPoints(name = "TimerScreen", route = "timer_route")
    object TableScreen : NavPoints(name = "TableScreen", route = "table_screen")
}