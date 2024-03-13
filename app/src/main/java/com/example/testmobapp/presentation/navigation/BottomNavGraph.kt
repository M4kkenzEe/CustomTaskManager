package com.example.testmobapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testmobapp.presentation.view.TableScreen
import com.example.testmobapp.presentation.view.TimerView

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.TasksScreen.route
    ) {
        composable(BottomBarScreens.AddTaskScreen.route) {

        }

        composable(BottomBarScreens.TasksScreen.route) {
            TableScreen()
        }

        composable(BottomBarScreens.FocusScreen.route) {
            TimerView()
        }

        composable(BottomBarScreens.ProgressScreen.route) {

        }
    }
}