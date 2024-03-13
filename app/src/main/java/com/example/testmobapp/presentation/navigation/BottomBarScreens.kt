package com.example.testmobapp.presentation.navigation

import com.example.testmobapp.R

sealed class BottomBarScreens(val route: String, val title: String, val icon: Int) {
    object AddTaskScreen : BottomBarScreens(
        route = "route_add",
        title = "Задачи",
        icon = R.drawable.ic_add_task
    )

    object FocusScreen : BottomBarScreens(
        route = "route_focus",
        title = "Сфокусироваться",
        icon = R.drawable.ic_focus
    )

    object TasksScreen : BottomBarScreens(
        route = "route_tasks",
        title = "Задачи",
        icon = R.drawable.ic_tasks
    )

    object ProgressScreen : BottomBarScreens(
        route = "route_progress",
        title = "Прогресс",
        icon = R.drawable.ic_progress
    )

}
