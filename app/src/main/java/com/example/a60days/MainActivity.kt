package com.example.a60days

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.edit
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a60days.ui.addhabit.AddHabitRoute
import com.example.a60days.ui.edithabit.EditHabitRoute
import com.example.a60days.ui.home.HomeRoute
import com.example.a60days.ui.settings.SettingsScreen
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

@Composable
fun SixtyDaysApp() {
    // Handles Navigation
    val navController = rememberNavController()

    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("settings", MODE_PRIVATE) }

    // Save dark theme preference
    var darkTheme by remember {
        mutableStateOf(prefs.getBoolean("dark_theme", false))
    }

    SixtyDaysTheme(darkTheme = darkTheme) {

        //Start with welcome screen
        NavHost(navController = navController, startDestination = "welcome") {

            composable("welcome") {
                WelcomeScreen(
                    onContinue = { navController.navigate("home") }
                )
            }

            // Continue to home screen
            composable("home") {
                HomeRoute(
                    onAddHabit = { navController.navigate("add") },
                    onHabitClick = { habitId -> navController.navigate("edit/$habitId") },
                    onSettings = { navController.navigate("settings") }
                )
            }

            // Then other screens can be clicked
            composable("add") {
                AddHabitRoute(
                    onSaved = { navController.popBackStack() },
                    onTitleClick = { navController.navigate("home") },
                    onSettingsClick = { navController.navigate("settings") }
                )
            }

            // OnTitleClick handles going back to home screen when banner is clicked
            composable(
                route = "edit/{habitId}",
                arguments = listOf(navArgument("habitId") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("habitId")!!
                EditHabitRoute(
                    habitId = id,
                    onDone = { navController.popBackStack() },
                    onTitleClick = { navController.navigate("home") },
                    onSettingsClick = { navController.navigate("settings") }
                )
            }

            composable("settings") {
                SettingsScreen(
                    darkTheme = darkTheme,
                    onThemeChange = {
                        darkTheme = it
                        prefs.edit { putBoolean("dark_theme", it) }
                    },
                    onTitleClick = { navController.navigate("home") },
                    onSettingsClick = { navController.navigate("settings") }
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    SixtyDaysApp()
}
