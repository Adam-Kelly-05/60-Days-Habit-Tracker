package com.example.a60days.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
    // This seporated completed and ongoing habits be checking between completed being >= total days
    val ongoingHabits = habits.filter { it.completedDays < it.totalDays }
    val completedHabits = habits.filter { it.completedDays >= it.totalDays }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    "60 Days",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                ) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
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
            FloatingActionButton(
                onClick = onAddHabit,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Habit",
                    modifier = Modifier.size(36.dp)
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
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (ongoingHabits.isEmpty()) {
                item {
                    Text("No ongoing habits yet.", color = MaterialTheme.colorScheme.primary)
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
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (completedHabits.isEmpty()) {
                item {
                    Text("No completed habits yet.", color = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.height(16.dp))
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
    val progress = if (habit.totalDays > 0) {
        (habit.completedDays.toFloat() / habit.totalDays).coerceIn(0f, 1f) // Make total days not be negitive
    } else {
        0f
    }

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(habit.name, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text(habit.description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)

            Spacer(Modifier.height(12.dp))

            LinearProgressIndicator( // Fill the bar depending on the completed days
                progress = { progress },
                modifier = Modifier.fillMaxWidth(),
                trackColor = Color.White
            )

            Spacer(Modifier.height(4.dp))

            Text("${habit.completedDays} / ${habit.totalDays} days", color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
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
