package com.example.a60days.ui.home

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a60days.SixtyDaysApplication
import com.example.a60days.data.Habit

@Composable
fun HomeRoute(
    onAddHabit: () -> Unit,
    onHabitClick: (Int) -> Unit,
    onSettings: () -> Unit,
    viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            (LocalContext.current.applicationContext as SixtyDaysApplication).habitRepository
        )
    )
) {
    val habits: List<Habit> by viewModel.habits.collectAsState(initial = emptyList())

    HomeScreen(
        habits = habits,
        onAddHabit = onAddHabit,
        onHabitClick = onHabitClick,
        onSettings = onSettings
    )
}
