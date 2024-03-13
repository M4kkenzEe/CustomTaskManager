package com.example.testmobapp.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testmobapp.presentation.navigation.BottomBarScreens
import com.example.testmobapp.presentation.navigation.BottomNavGraph
import com.example.testmobapp.presentation.ui.theme.GrayE3
import com.example.testmobapp.presentation.utils.showToast

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomBar(navController = navController) }) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreens.TasksScreen,
        BottomBarScreens.ProgressScreen,
        BottomBarScreens.FocusScreen,
        BottomBarScreens.AddTaskScreen,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val currentScreen = remember { mutableIntStateOf(0) }
    val previousScreen = remember { mutableIntStateOf(0) }

    BottomAppBar(
        containerColor = GrayE3,
        modifier = Modifier.height(52.dp)
    ) {
        screens.forEachIndexed { index, screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController,
                index = index,
                currentScreen = currentScreen,
                previousScreen = previousScreen
            )
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreens,
    currentDestination: NavDestination?,
    navController: NavHostController,
    index: Int,
    currentScreen: MutableState<Int>,
    previousScreen: MutableState<Int>
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    val context = LocalContext.current

    NavigationBarItem(
        selected = selected,
        onClick = {
            if (currentScreen.value != index) {
                currentScreen.value = index
            } else {
                if (previousScreen.value == index && index == 0) {
                    showToast(context, "Вы уже на главном экране")
                } else {
                    previousScreen.value = index
                }
            }

            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }, icon = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = screen.icon),
                    contentDescription = "",
                )
                Text(text = screen.title, fontSize = 10.sp, fontWeight = FontWeight(400))
            }
        }
    )
}