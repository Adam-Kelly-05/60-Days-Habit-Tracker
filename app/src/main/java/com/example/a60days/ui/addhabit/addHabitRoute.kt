package com.example.a60days.ui.addhabit

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a60days.SixtyDaysApplication

@Composable
fun AddHabitRoute(
    onSaved: () -> Unit,
    viewModel: AddHabitViewModel = viewModel(
        factory = AddHabitViewModelFactory(
            (LocalContext.current.applicationContext as SixtyDaysApplication).habitRepository
        )
    )
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var totalDays by remember { mutableStateOf("") }

    AddHabitScreen(
        name = name,
        description = description,
        totalDays = totalDays,

        onNameChange = { name = it },
        onDescriptionChange = { description = it },
        onTotalDaysChange = { totalDays = it },

        onSave = {
            if (name.isNotEmpty()) {
                viewModel.saveHabit(
                    name = name,
                    description = description,
                    totalDays = totalDays.toIntOrNull() ?: 0,
                    onSaved = onSaved
                )
            }
        }
    )
}
