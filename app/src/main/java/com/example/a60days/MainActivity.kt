package com.example.a60days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a60days.ui.home.HabitUi
import com.example.a60days.ui.home.HomeScreen
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
            HomeScreen(
                habits = listOf(
                    HabitUi(1, "Read", "Read 20 pages", 10, 60)
                ),
                onAddHabit = { navController.navigate("add") },
                onHabitClick = { id -> navController.navigate("edit/$id") },
                onSettings = { navController.navigate("settings") }
            )
        }

        composable("add"){

        }

        composable("edit/{habitId}") {

        }

        composable("settings") {

        }
    }
}
