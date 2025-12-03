package com.example.a60days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a60days.ui.addhabit.AddHabitRoute
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
fun GreetingPreview() {
    SixtyDaysTheme {
        SixtyDaysApp()
    }
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
            "edit/{habitId}",
//            arguments = listOf(navArgument("habitId") { type = NavType.IntType })
        ) {
//            EditHabitRoute()
        }

        composable("settings") {
            // SettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}
