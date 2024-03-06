package com.example.testmobapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testmobapp.presentation.newview.SplashScreen
import com.example.testmobapp.presentation.newview.TableScreen
import com.example.testmobapp.presentation.view.TimerView

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavPoints.SplashScreen.route) {

        composable(NavPoints.SplashScreen.route) {
            SplashScreen {
                navController.navigate(NavPoints.TableScreen.route)
            }
        }

        composable(NavPoints.TableScreen.route) {
            TableScreen()
        }

        composable(NavPoints.TimerScreen.route) {
            TimerView()
        }
    }

}