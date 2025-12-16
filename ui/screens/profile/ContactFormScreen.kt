package com.example.tiendaidnp.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tiendaidnp.ui.components.CompactBasicTextField
import com.example.tiendaidnp.ui.components.buttons.PrimaryButton
import com.example.tiendaidnp.ui.components.TitleBackTopBar
import com.example.tiendaidnp.ui.components.buttons.ButtonType
import com.example.tiendaidnp.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactFormScreen(
    onNavigateBack: () -> Unit
) {
    val colors = AppTheme.customColors

    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var asunto by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    // Estado para controlar el diálogo
    var showDialog by remember { mutableStateOf(false) }

    // Verificar si el formulario está completo
    val isFormValid = nombre.isNotBlank() && email.isNotBlank() && asunto.isNotBlank() && mensaje.isNotBlank()

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
            CompactBasicTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = "Nombre",
                placeholder = "Ingresa tu nombre",
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

//            Text("Email", style = MaterialTheme.typography.displayMedium)
//            OutlinedTextField(
//                value = email,
//                onValueChange = { email = it },
//                label = { Text("Email", fontSize = 12.sp) },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(42.dp),
//                singleLine = true
//            )
            CompactBasicTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                placeholder = "Ingresa tu email",
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

//            Text("Asunto", style = MaterialTheme.typography.displayMedium)
//            OutlinedTextField(
//                value = asunto,
//                onValueChange = { asunto = it },
//                label = { Text("Asunto", fontSize = 12.sp) },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(42.dp),
//                singleLine = true
//            )
            CompactBasicTextField(
                value = asunto,
                onValueChange = { asunto = it },
                label = "Asunto",
                placeholder = "Ingresa tu asunto",
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

//            Text("Mensaje", style = MaterialTheme.typography.displayMedium)
//            OutlinedTextField(
//                value = mensaje,
//                onValueChange = { mensaje = it },
//                label = { Text("Mensaje", fontSize = 12.sp) },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(168.dp),
//                maxLines = 6
//            )
            CompactBasicTextField(
                value = mensaje,
                onValueChange = { mensaje = it },
                label = "Mensaje",
                placeholder = "Ingresa tu mensaje",
                modifier = Modifier.fillMaxWidth(),
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
                    onClick = { showDialog = true },
                    enabled = isFormValid // 🔹 Solo activo cuando el formulario está completo
                )
            }
        }
    }

    // 🔹 Alerta de confirmación
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Formulario enviado") },
            text = { Text("Gracias, ${nombre.trim()}.\nTu mensaje ha sido enviado correctamente.") }
        )
    }
}

