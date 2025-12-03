package com.example.a60days.ui.edithabit

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a60days.SixtyDaysApplication
import com.example.a60days.data.Habit

@Composable
fun EditHabitRoute(
    habitId: Int,
    onDone: () -> Unit,
    viewModel: EditHabitViewModel = viewModel(
        factory = EditHabitViewModelFactory(
            (LocalContext.current.applicationContext as SixtyDaysApplication).habitRepository,
            habitId
        )
    )
) {
    val habit by viewModel.habit.collectAsState(initial = null)

    EditHabitScreen(
        habit = habit,
        onSave = { name, description, total, completed ->
            viewModel.updateHabit(name, description, total, completed)
            onDone()
        },
        onBack = onDone
    )

}
