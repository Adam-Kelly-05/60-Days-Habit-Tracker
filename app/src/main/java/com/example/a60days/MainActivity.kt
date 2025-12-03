package com.example.a60days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a60days.ui.addhabit.AddHabitRoute
import com.example.a60days.ui.edithabit.EditHabitRoute
import com.example.a60days.ui.home.HomeRoute
import com.example.a60days.ui.theme.SixtyDaysTheme
import com.example.a60days.ui.welcome.WelcomeScreen

annotation class ERROR

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SixtyDaysTheme {
                SixtyDaysApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    SixtyDaysApp()
}

@Composable
fun SixtyDaysApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {

        composable("welcome") {
            WelcomeScreen(
                onContinue = { navController.navigate("home") }
            )
        }

        composable("home") {
            HomeRoute(
                onAddHabit = { navController.navigate("add") },
                onHabitClick = { habitId -> navController.navigate("edit/$habitId") },
                onSettings = { navController.navigate("settings") }
            )
        }

        composable("add") {
            AddHabitRoute(onSaved = { navController.popBackStack() })
        }

        composable(
            route = "edit/{habitId}",
            arguments = listOf(navArgument("habitId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("habitId")!!
            EditHabitRoute(habitId = id, onDone = { navController.popBackStack() })
        }


        composable("settings") {
            // SettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}
