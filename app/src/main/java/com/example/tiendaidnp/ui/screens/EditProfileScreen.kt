package com.example.tiendaidnp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tiendaidnp.R
import com.example.tiendaidnp.data.repository.UserPreferencesRepository
import com.example.tiendaidnp.data.model.UserProfile
import com.example.tiendaidnp.ui.components.TitleBackTopBar
import com.example.tiendaidnp.ui.navigation.Routes
import kotlinx.coroutines.launch
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavController,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val userRepo = remember { UserPreferencesRepository(context) }
    val scope = rememberCoroutineScope()

    // Estado actual del perfil
    val userProfileFlow = userRepo.userProfileFlow.collectAsState(
        initial = UserProfile("", "", "", "", "", "")
    )
    var userProfile by remember { mutableStateOf(userProfileFlow.value) }

    // Actualiza el estado cuando cambian los valores del Flow
    LaunchedEffect(userProfileFlow.value) {
        userProfile = userProfileFlow.value
    }

    Scaffold(
        topBar = {
            TitleBackTopBar(
                title = "Editar Datos",
                onBackClick = onNavigateBack
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen de perfil
            Box(
                modifier = Modifier.size(120.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier.size(120.dp)
                )
                IconButton(onClick = { /* cambiar imagen */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Editar foto"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos editables
            OutlinedTextField(
                value = userProfile.name,
                onValueChange = { userProfile = userProfile.copy(name = it) },
                label = { Text("Nombres") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = userProfile.lastname,
                onValueChange = { userProfile = userProfile.copy(lastname = it) },
                label = { Text("Apellidos") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = userProfile.email,
                onValueChange = { userProfile = userProfile.copy(email = it) },
                label = { Text("E-mail") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            OutlinedTextField(
                value = userProfile.phone,
                onValueChange = { userProfile = userProfile.copy(phone = it) },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            OutlinedTextField(
                value = userProfile.address,
                onValueChange = { userProfile = userProfile.copy(address = it) },
                label = { Text("Dirección") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = userProfile.password,
                onValueChange = { userProfile = userProfile.copy(password = it) },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Botón de guardar
            Button(
                onClick = {
                    scope.launch {
                        userRepo.saveUserProfile(userProfile)
                        navController.navigate(Routes.PROFILE) {
                            popUpTo(Routes.EDIT_PROFILE) { inclusive = true }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar cambios")
            }
        }
    }
}