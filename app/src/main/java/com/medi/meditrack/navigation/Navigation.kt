package com.medi.meditrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.medi.meditrack.navigation.Screen.AddMedicationScreen
import com.medi.meditrack.screen.AddMedicationScreen
import com.medi.meditrack.screen.WelcomeScreen


sealed class Screen(val string: String) {
    object WelcomeScreen: Screen("welcomeScreen")
    object AddMedicationScreen: Screen("addMedicationScreen")

}


@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.WelcomeScreen.string
    ) {
        composable(Screen.WelcomeScreen.string){
            WelcomeScreen(
                onNext = {
                    navController.navigate(AddMedicationScreen.string)
                }
            )
        }

        composable(AddMedicationScreen.string) {
            AddMedicationScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
