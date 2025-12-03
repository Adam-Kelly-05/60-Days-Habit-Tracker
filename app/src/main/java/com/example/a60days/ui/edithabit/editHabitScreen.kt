package com.example.a60days.ui.edithabit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a60days.data.Habit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditHabitScreen(
    habit: Habit?,
    onSave: (String, String, Int, Int) -> Unit,
    onBack: () -> Unit
) {
    if (habit == null) {
        Text("Loading…")
        return
    }

    var name by remember { mutableStateOf(habit.name) }
    var description by remember { mutableStateOf(habit.description) }
    var totalDays by remember { mutableStateOf(habit.totalDays.toString()) }
    var completedDays by remember { mutableStateOf(habit.completedDays.toString()) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Edit Habit") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = totalDays,
                onValueChange = { totalDays = it },
                label = { Text("Total Days") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = completedDays,
                onValueChange = { completedDays = it },
                label = { Text("Completed Days") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    onSave(
                        name,
                        description,
                        totalDays.toInt(),
                        completedDays.toInt()
                    )
                },
                enabled = name.isNotEmpty()
            ) {
                Text("Save")
            }
        }
    }
}
