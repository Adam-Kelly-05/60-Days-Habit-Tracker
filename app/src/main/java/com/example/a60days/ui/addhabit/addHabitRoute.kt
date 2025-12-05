package com.example.a60days.ui.addhabit

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a60days.SixtyDaysApplication
import com.example.a60days.ui.reusableComponents.CameraActivity

@Composable
fun AddHabitRoute(
    onSaved: () -> Unit,
    onTitleClick: () -> Unit,
    onSettingsClick: () -> Unit,
    viewModel: AddHabitViewModel = viewModel(
        factory = AddHabitViewModelFactory(
            (LocalContext.current.applicationContext as SixtyDaysApplication).habitRepository
        )
    )
) {
    val context = LocalContext.current

    // UI states
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var totalDays by remember { mutableStateOf("") }

    // NEW: store captured photo
    var photoUri by remember { mutableStateOf<String?>(null) }

    // NEW: launcher for CameraActivity (returns URI)
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.getStringExtra("photo_uri")
            if (uri != null) {
                photoUri = uri
            }
        }
    }

    AddHabitScreen(
        name = name,
        description = description,
        totalDays = totalDays,
        photoUri = photoUri,   // NEW

        onNameChange = { name = it },
        onDescriptionChange = { description = it },
        onTotalDaysChange = { totalDays = it },

        // NEW: camera button
        onTakePhoto = {
            val intent = Intent(context, CameraActivity::class.java)
            cameraLauncher.launch(intent)
        },

        onSave = {
            if (name.isNotEmpty()) {
                val totalDaysValue = totalDays.toIntOrNull()?.coerceAtLeast(1) ?: 1
                viewModel.saveHabit(
                    name = name,
                    description = description,
                    totalDays = totalDaysValue,
                    photoUri = photoUri,   // NEW
                    onSaved = onSaved
                )
            }
        },

        onTitleClick = onTitleClick,
        onSettingsClick = onSettingsClick
    )
}
