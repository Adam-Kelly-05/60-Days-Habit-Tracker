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

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var totalDays by remember { mutableStateOf("") }

    var photoUri by remember { mutableStateOf<String?>(null) }

    // This calls the camera activity
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
        photoUri = photoUri,

        onNameChange = { name = it },
        onDescriptionChange = { description = it },
        onTotalDaysChange = { totalDays = it },

        onTakePhoto = {
            val intent = Intent(context, CameraActivity::class.java)
            cameraLauncher.launch(intent)
        },

        onSave = {
            if (name.isNotEmpty()) {
                val totalDaysValue = totalDays.toIntOrNull()?.coerceAtLeast(1) ?: 1 // Can not be less than 1
                viewModel.saveHabit(
                    name = name,
                    description = description,
                    totalDays = totalDaysValue,
                    photoUri = photoUri,
                    onSaved = onSaved
                )
            }
        },

        onTitleClick = onTitleClick,
        onSettingsClick = onSettingsClick
    )
}
