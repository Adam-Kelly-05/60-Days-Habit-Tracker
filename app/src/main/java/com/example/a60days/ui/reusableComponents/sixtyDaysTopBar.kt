package com.example.a60days.ui.reusableComponents

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SixtyDaysTopBar(
    onTitleClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "60 Days",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                modifier = Modifier.clickable { onTitleClick() }
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        actions = {
            IconButton(onClick = onSettingsClick) { // Go to settings screen and load settings icon
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            }
        }
    )
}
