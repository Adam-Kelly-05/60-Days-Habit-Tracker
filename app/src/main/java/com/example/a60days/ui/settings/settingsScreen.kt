package com.example.a60days.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a60days.ui.reusableComponents.SixtyDaysTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onTitleClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
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
                .padding(16.dp)
        ) {
            Text(
                "Settings",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("Dark Mode", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = darkTheme,
                    onCheckedChange = onThemeChange,
                    colors = SwitchDefaults.colors(
                        uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                        uncheckedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                        uncheckedBorderColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        darkTheme = false,
        onThemeChange = {},
        onTitleClick = {},
        onSettingsClick = {}
    )
}
