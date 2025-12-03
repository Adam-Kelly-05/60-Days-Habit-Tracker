package com.example.a60days.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a60days.SixtyDaysApplication

@Composable
fun HomeRoute(
    onAddHabit: () -> Unit,
    onHabitClick: (Int) -> Unit,
    onSettings: () -> Unit,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(
        (LocalContext.current.applicationContext as SixtyDaysApplication).habitRepository
    ))
) {
    val habits by viewModel.habits.collectAsState(initial = emptyList())

    HomeScreen(
        habits = habits,
        onAddHabit = onAddHabit,
        onHabitClick = onHabitClick,
        onSettings = onSettings
    )
}

