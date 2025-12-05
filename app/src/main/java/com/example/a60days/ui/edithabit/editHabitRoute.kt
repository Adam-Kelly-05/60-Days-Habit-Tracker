package com.example.a60days.ui.edithabit

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
fun EditHabitRoute(
    habitId: Int,
    onDone: () -> Unit,
    onTitleClick: () -> Unit,
    onSettingsClick: () -> Unit,
    viewModel: EditHabitViewModel = viewModel(
        factory = EditHabitViewModelFactory(
            (LocalContext.current.applicationContext as SixtyDaysApplication).habitRepository,
            habitId
        )
    )
) {
    val context = LocalContext.current
    val habit by viewModel.habit.collectAsState()

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var totalDays by remember { mutableStateOf("") }
    var completedDays by remember { mutableStateOf("") }
    var photoUri by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(habit) {
        habit?.let {
            name = it.name
            description = it.description
            totalDays = it.totalDays.toString()
            completedDays = it.completedDays.toString()
            photoUri = it.photoUri
        }
    }

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

    EditHabitScreen(
        habit = habit,
        photoUri = photoUri,
        onTakePhoto = {
            val intent = Intent(context, CameraActivity::class.java)
            cameraLauncher.launch(intent)
        },
        onSave = { n, d, t, c, newPhotoUri ->
            viewModel.updateHabit(
                name = n,
                description = d,
                totalDays = t,
                completedDays = c,
                photoUri = newPhotoUri
            )
            onDone()
        },
        onDelete = {
            viewModel.deleteHabit {
                onDone()
            }
        },
        onTitleClick = onTitleClick,
        onSettingsClick = onSettingsClick
    )
}
