package com.example.tiendaidnp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.example.tiendaidnp.ui.components.buttons.PrimaryButton
import com.example.tiendaidnp.ui.components.TitleBackTopBar
import com.example.tiendaidnp.ui.components.buttons.ButtonType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactFormScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TitleBackTopBar(
                title = "Contacto",
                onBackClick = onNavigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            var nombre by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var asunto by remember { mutableStateOf("") }
            var mensaje by remember { mutableStateOf("") }

            Text(
                text = "Nombre",
                style = MaterialTheme.typography.displayMedium
            )
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                textStyle = MaterialTheme.typography.displayMedium,
                singleLine = true
            )

            Text(
                text = "Email",
                style = MaterialTheme.typography.displayMedium
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                singleLine = true
            )

            Text(
                text = "Asunto",
                style = MaterialTheme.typography.displayMedium
            )
            OutlinedTextField(
                value = asunto,
                onValueChange = { asunto = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                textStyle = MaterialTheme.typography.displayMedium,
                singleLine = true
            )

            Text(
                text = "Mensaje",
                style = MaterialTheme.typography.displayMedium
            )
            OutlinedTextField(
                value = mensaje,
                onValueChange = { mensaje = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(168.dp),
                textStyle = MaterialTheme.typography.displayMedium,
                maxLines = 6
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(92.dp),
                contentAlignment = Alignment.Center
            ) {
                PrimaryButton(
                    text = "Enviar",
                    icon = true,
                    type = ButtonType.SLIM,
                    onClick = {
                        // TODO: Manejar envío del formulario
                    }
                )
            }
        }
    }
}
