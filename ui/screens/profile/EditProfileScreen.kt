package com.example.tiendaidnp.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.tiendaidnp.ui.components.CompactBasicTextField
import com.example.tiendaidnp.ui.components.buttons.ButtonType
import com.example.tiendaidnp.ui.components.buttons.PrimaryButton
import com.example.tiendaidnp.ui.theme.white

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
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen de perfil
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .padding(top = 16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier.size(120.dp)
                )
                IconButton(onClick = { /* cambiar imagen */ }) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .padding(8.dp)
                            .background(white),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = "Editar foto"
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Campos editables
                CompactBasicTextField(
                    value = userProfile.name,
                    onValueChange = { userProfile = userProfile.copy(name = it) },
                    label = "Nombres",
                    placeholder = "Ingresa tu nombre(s)",
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                CompactBasicTextField(
                    value = userProfile.lastname,
                    onValueChange = { userProfile = userProfile.copy(lastname = it) },
                    label = "Apellidos",
                    placeholder = "Ingresa tu apellido(s)",
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                CompactBasicTextField(
                    value = userProfile.email,
                    onValueChange = { userProfile = userProfile.copy(email = it) },
                    label = "E-mail",
                    placeholder = "Ingresa tu correo electrónico",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true
                )

                CompactBasicTextField(
                    value = userProfile.phone,
                    onValueChange = { userProfile = userProfile.copy(phone = it) },
                    label = "Teléfono",
                    placeholder = "Ingresa tu teléfono",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true
                )

                CompactBasicTextField(
                    value = userProfile.address,
                    onValueChange = { userProfile = userProfile.copy(address = it) },
                    label = "Dirección",
                    placeholder = "Ingresa tu dirección",
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                CompactBasicTextField(
                    value = userProfile.password,
                    onValueChange = { userProfile = userProfile.copy(password = it) },
                    label = "Contraseña",
                    placeholder = "Ingresa tu contraseña",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            // Botón de guardar
            PrimaryButton(
                text = "Guardar cambios",
                type = ButtonType.SLIM,
                onClick = {
                    scope.launch {
                        userRepo.saveUserProfile(userProfile)
                        navController.navigate(Routes.PROFILE) {
                            popUpTo(Routes.EDIT_PROFILE) { inclusive = true }
                        }
                    }
                }
            )
        }
    }
}