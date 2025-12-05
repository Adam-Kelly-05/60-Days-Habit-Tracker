package com.example.a60days.ui.edithabit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.a60days.data.Habit
import com.example.a60days.ui.reusableComponents.SixtyDaysTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditHabitScreen(
    habit: Habit?,
    photoUri: String?,
    onTakePhoto: () -> Unit,
    onSave: (String, String, Int, Int, String?) -> Unit,
    onDelete: () -> Unit,
    onTitleClick: () -> Unit,
    onSettingsClick: () -> Unit
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
            SixtyDaysTopBar( // Call reusable top bar
                onTitleClick = onTitleClick,
                onSettingsClick = onSettingsClick
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Edit Habit",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Habit Name") },
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

            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { // Both boxes on the same line
                OutlinedTextField(
                    value = totalDays,
                    onValueChange = { totalDays = it },
                    label = { Text("Total Days") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(12.dp))
                OutlinedTextField(
                    value = completedDays,
                    onValueChange = { completedDays = it },
                    label = { Text("Completed") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = onTakePhoto,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Take Photo")
            }

            if (photoUri != null) {
                Spacer(Modifier.height(12.dp))
                Image(
                    painter = rememberAsyncImagePainter(photoUri), // Call camera module
                    contentDescription = "Habit Photo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    onSave(
                        name,
                        description,
                        totalDays.toIntOrNull() ?: 0,
                        completedDays.toIntOrNull() ?: 0,
                        photoUri
                    )
                },
                enabled = name.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }

            Button( // Red button to show danger
                onClick = { onDelete() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Delete Habit", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    val habit = Habit(
        id = 1,
        name = "",
        description = "",
        totalDays = 0,
        completedDays = 0
    )
    EditHabitScreen(
        habit = habit,
        photoUri = null,
        onTakePhoto = { },
        onSave = { _, _, _, _, _ -> },
        onDelete = { },
        onTitleClick = { },
        onSettingsClick = { }
    )
}