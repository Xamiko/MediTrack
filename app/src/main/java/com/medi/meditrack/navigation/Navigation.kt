package com.medi.meditrack.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.medi.meditrack.navigation.Screen.AddMedicationScreen
import com.medi.meditrack.navigation.Screen.SettingScreen
import com.medi.meditrack.screen.AddMedicationScreen
import com.medi.meditrack.screen.HomeScreen
import com.medi.meditrack.screen.SettingScreen
import com.medi.meditrack.screen.WelcomeScreen


sealed class Screen(val string: String) {
    object WelcomeScreen: Screen("welcomeScreen")
    object HomeScreen : Screen("homeScreen")
    object SettingScreen : Screen("settingScreen")
    object AddMedicationScreen: Screen("addMedicationScreen")


}

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    // Describe the current route to show/hide bottom bar
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf<String>("homeScreen", "settingScreen")) {
                BottomNavigationBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            )
        }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.string,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.WelcomeScreen.string) {
                WelcomeScreen(
                    onNext = {
                        navController.navigate(AddMedicationScreen.string)
                    }
                )
            }

            composable(AddMedicationScreen.string) {
                AddMedicationScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Screen.HomeScreen.string) {
                HomeScreen(
                    OnAddMedicationClick = {
                        navController.navigate(AddMedicationScreen.string)
                    }
                )
            }

            composable(SettingScreen.string) {
                SettingScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar (
    currentRoute: String?,
    onNavigate: (String) -> Unit
){
    NavigationBar {

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == Screen.HomeScreen.string,
            onClick = { onNavigate(Screen.HomeScreen.string) }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = currentRoute == SettingScreen.string,
            onClick = { onNavigate(SettingScreen.string) }
        )

        }

    }

