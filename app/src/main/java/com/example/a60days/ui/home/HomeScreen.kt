package com.example.a60days.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("60 Days") },
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
            items(habits) { habit ->
                HabitCard(
                    habit = habit,
                    onClick = { onHabitClick(habit.id) }
                )
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
                progress = {habit.completedDays.toFloat() / habit.totalDays.toFloat()},
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
        Habit(id = 1, name = "Read for 15 minutes", description = "Read a book or an article.", completedDays = 10, totalDays = 60),
        Habit(id = 2, name = "Morning Exercise", description = "A quick 10-minute workout.", completedDays = 35, totalDays = 60),
        Habit(id = 3, name = "Drink 8 glasses of water", description = "Stay hydrated throughout the day.", completedDays = 58, totalDays = 60)
    )
    HomeScreen(
        habits = sampleHabits,
        onAddHabit = {},
        onHabitClick = {},
        onSettings = {}
    )
}
