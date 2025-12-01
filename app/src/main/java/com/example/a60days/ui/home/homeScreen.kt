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
import com.example.a60days.ui.theme.SixtyDaysTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    habits: List<HabitUi>,
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
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddHabit) {
                Icon(Icons.Default.Add, contentDescription = "Add habit")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).padding(16.dp)
        ) {
            items(habits) { habit ->
                HabitCard(habit = habit, onClick = { onHabitClick(habit.id) })
            }
        }
    }
}

@Composable
fun HabitCard(habit: HabitUi, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(habit.name, style = MaterialTheme.typography.titleMedium)
            Text(habit.description)
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { habit.completedDays.toFloat() / habit.totalDays.toFloat() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    SixtyDaysTheme {
        HomeScreen(
            habits = listOf(
                HabitUi(
                    id = 1,
                    name = "Read a book",
                    description = "Read for at least 15 minutes every day.",
                    completedDays = 25,
                    totalDays = 60
                ),
                HabitUi(
                    id = 2,
                    name = "Exercise",
                    description = "Do a 30-minute workout session.",
                    completedDays = 45,
                    totalDays = 60
                )
            ), onAddHabit = {}, onHabitClick = {}, onSettings = {}
        )
    }
}