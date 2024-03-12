package com.example.testmobapp.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.testmobapp.presentation.navigation.bottom.BottomBarScreens
import com.example.testmobapp.presentation.navigation.bottom.BottomNavGraph
import com.example.testmobapp.presentation.ui.theme.GrayE3

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomBar(navController = navController) }) {
        BottomNavGraph(navController = navController)
    }

//    NavHost(navController = navController, startDestination = NavPoints.SplashScreen.route) {
//
//        composable(NavPoints.SplashScreen.route) {
//            SplashScreen {
//                navController.navigate(NavPoints.TableScreen.route)
//            }
//        }
//
//        composable(NavPoints.TableScreen.route) {
//            TableScreen()
//        }
//
//        composable(NavPoints.TimerScreen.route) {
//            TimerView()
//        }
//    }


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

    BottomAppBar(containerColor = GrayE3) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController,
                modifier = Modifier.weight(1f)
            )
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreens,
    currentDestination: NavDestination?,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    Box(
        modifier = modifier
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "",
                modifier = Modifier.size(42.dp)
            )
            Text(text = screen.title, fontSize = 10.sp, fontWeight = FontWeight(400))
        }
    }
}