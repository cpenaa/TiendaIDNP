package com.example.tiendaidnp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tiendaidnp.ui.theme.AppTheme
import com.example.tiendaidnp.ui.viewmodel.ThemeViewModel
import com.example.tiendaidnp.data.datastore.ThemeMode
import com.example.tiendaidnp.ui.components.TitleBackTopBar

@Composable
fun SettingsScreen(
    viewModel: ThemeViewModel,
    onNavigateBack: () -> Unit
) {
    val colors = AppTheme.customColors
    val themeModeState by viewModel.themeMode.collectAsState()

    Scaffold(
        topBar = {
            TitleBackTopBar(
                title = "Configuración de tema",
                onBackClick = onNavigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Seleccione el tema",
                style = MaterialTheme.typography.titleMedium
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = colors.white)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    ThemeMode.values().forEach { mode ->
                        val isSelected = themeModeState == mode
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.updateTheme(mode) }
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { viewModel.updateTheme(mode) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = when (mode) {
                                    ThemeMode.LIGHT -> "Claro"
                                    ThemeMode.DARK -> "Oscuro"
                                    ThemeMode.SYSTEM -> "Sistema"
                                },
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
