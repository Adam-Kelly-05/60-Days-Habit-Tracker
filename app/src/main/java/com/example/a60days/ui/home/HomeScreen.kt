package com.example.a60days.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a60days.data.Habit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    habits: List<Habit>,
    onAddHabit: () -> Unit,
    onHabitClick: (Int) -> Unit,
    onSettings: () -> Unit
) {
    val ongoingHabits = habits.filter { it.completedDays < it.totalDays }
    val completedHabits = habits.filter { it.completedDays >= it.totalDays }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    "60 Days",
                    style = MaterialTheme.typography.headlineLarge,
                ) },
                actions = {
                    IconButton(onClick = onSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddHabit) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Habit"
                )
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "Ongoing Habits",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (ongoingHabits.isEmpty()) {
                item {
                    Text("No ongoing habits yet.")
                    Spacer(Modifier.height(16.dp))
                }
            } else {
                items(ongoingHabits) { habit ->
                    HabitCard(
                        habit = habit,
                        onClick = { onHabitClick(habit.id) }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Text(
                    text = "Completed Habits",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (completedHabits.isEmpty()) {
                item {
                    Text("No completed habits yet.")
                }
            } else {
                items(completedHabits) { habit ->
                    HabitCard(
                        habit = habit,
                        onClick = { onHabitClick(habit.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun HabitCard(habit: Habit, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(habit.name, style = MaterialTheme.typography.titleMedium)
            Text(habit.description, style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { habit.completedDays.toFloat() / habit.totalDays.toFloat() },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(4.dp))

            Text("${habit.completedDays} / ${habit.totalDays} days")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val sampleHabits = listOf(
        Habit(id = 1, name = "Read", description = "Read 15 min", completedDays = 10, totalDays = 60),
        Habit(id = 2, name = "Exercise", description = "Morning workout", completedDays = 60, totalDays = 60),
        Habit(id = 3, name = "Water", description = "Drink 8 cups", completedDays = 58, totalDays = 60)
    )
    HomeScreen(
        habits = sampleHabits,
        onAddHabit = {},
        onHabitClick = {},
        onSettings = {}
    )
}
