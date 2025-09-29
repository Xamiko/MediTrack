package com.medi.meditrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.medi.meditrack.screen.GreetingPillScreen
import com.medi.meditrack.screen.GreetingScreen


sealed class Screen(val string: String) {
    object Greating: Screen("greating")
    object GreatingPill: Screen("greatingPill")

}


@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Greating.string
    ) {
        composable(Screen.Greating.string) {
            GreetingScreen(
                onNext = {
                    navController.navigate(Screen.GreatingPill.string)
                }
            )
        }

        composable(Screen.GreatingPill.string) {
            GreetingPillScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}