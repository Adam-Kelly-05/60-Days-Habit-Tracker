package com.example.a60days.ui.addhabit

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a60days.ui.reusableComponents.SixtyDaysTopBar
import coil.compose.rememberAsyncImagePainter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHabitScreen(
    name: String,
    description: String,
    totalDays: String,
    photoUri: String?,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onTotalDaysChange: (String) -> Unit,
    onTakePhoto: () -> Unit,
    onSave: () -> Unit,
    onTitleClick: () -> Unit,
    onSettingsClick: () -> Unit
){
    Scaffold(
        topBar = {
            SixtyDaysTopBar(
                onTitleClick = onTitleClick,
                onSettingsClick = onSettingsClick
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Add Habit",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Habit Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = description,
                onValueChange = onDescriptionChange,
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            Button( // Call camera activity
                onClick = onTakePhoto,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Take Photo")
            }

            Spacer(modifier = Modifier.height(12.dp))

            photoUri?.let {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = Uri.parse(it)),
                            contentDescription = "Attached photo",
                            modifier = Modifier.fillMaxWidth().height(200.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = totalDays,
                onValueChange = onTotalDaysChange,
                label = { Text("Total Days") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = onSave,
                enabled = name.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    var name by remember { mutableStateOf("Read a book") }
    var description by remember { mutableStateOf("Read for 15 minutes every day") }
    var totalDays by remember { mutableStateOf("60") }
    AddHabitScreen(
        name = name,
        description = description,
        totalDays = totalDays,
        onNameChange = { name = it },
        onDescriptionChange = { description = it },
        onTotalDaysChange = { totalDays = it },
        onSave = {},
        onTitleClick = {},
        onSettingsClick = {},
        photoUri = null,
        onTakePhoto = {}
    )
}
