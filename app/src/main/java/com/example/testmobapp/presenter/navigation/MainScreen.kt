package com.example.testmobapp.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testmobapp.presenter.view.TableTaskScreen
import com.example.testmobapp.presenter.view.TimerView

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavPoints.TableScreen.route) {

        composable(NavPoints.TableScreen.route) {
            TableTaskScreen(
                taskCLick = { navController.navigate(NavPoints.TimerScreen.route) },
                popUpScreen = { navController.navigate(NavPoints.TimerScreen.route) }
//                onLongCLickTask = { navController.navigate(NavPoints.TimerScreen.route) }
            )
        }

        composable(NavPoints.TimerScreen.route) {
            TimerView()
        }
    }

}