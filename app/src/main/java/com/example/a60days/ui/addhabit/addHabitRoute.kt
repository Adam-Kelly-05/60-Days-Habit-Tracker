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
    var name by remember { mutableStateOf(viewModel.name) }
    var description by remember { mutableStateOf(viewModel.description) }
    var totalDays by remember { mutableStateOf(viewModel.totalDays) }

    AddHabitScreen(
        name = name,
        description = description,
        totalDays = totalDays,
        onNameChange = {
            viewModel.name = it
        },
        onDescriptionChange = {
            viewModel.description = it
        },
        onTotalDaysChange = {
            viewModel.totalDays = it
        },
        onSave = {
            viewModel.saveHabit(onSaved)
        }
    )
}
