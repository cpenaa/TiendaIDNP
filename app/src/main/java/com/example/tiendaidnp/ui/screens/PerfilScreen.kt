package com.example.tiendaidnp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tiendaidnp.R
import com.example.tiendaidnp.data.repository.UserPreferencesRepository
import com.example.tiendaidnp.data.model.UserProfile
import com.example.tiendaidnp.ui.components.buttons.PrimaryButton
import com.example.tiendaidnp.ui.components.ProductsBottomBar
import com.example.tiendaidnp.ui.components.ProductsTopBar
import com.example.tiendaidnp.ui.components.ScreenTitle
import com.example.tiendaidnp.ui.components.buttons.ButtonType
import com.example.tiendaidnp.ui.navigation.Routes
import com.example.tiendaidnp.ui.theme.AppTheme
import com.example.tiendaidnp.ui.theme.Neutral30
import com.example.tiendaidnp.ui.theme.Neutral50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(navController: NavController) {
    val colors = AppTheme.customColors
    val context = LocalContext.current
    val userRepo = remember { UserPreferencesRepository(context) }

    // Escuchar los cambios del perfil almacenado
    val userProfile by userRepo.userProfileFlow.collectAsState(
        initial = UserProfile("", "", "", "", "", "")
    )

    Scaffold(
        topBar = { ProductsTopBar() },
        bottomBar = {
            ProductsBottomBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle("Perfil")
            // Imagen de perfil
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${userProfile.name} ${userProfile.lastname}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = colors.black
                    )
                    IconButton(
                        onClick = { navController.navigate(Routes.EDIT_PROFILE) }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = "Editar perfil",
                            modifier = Modifier
                                .size(18.dp)
                                .alpha(0.5f)
                        )
                    }
                }
                Text(
                    text = userProfile.email,
                    style = MaterialTheme.typography.bodySmall,
                    color = Neutral50
                )
                Spacer(modifier = Modifier.height(24.dp))
                // --- Secciones ---
                PerfilSection(
                    title = "Pedidos",
                    items = listOf("Pedidos recientes", "Historial de compras")
                )
                PerfilSection(
                    title = "Configuración",
                    items = listOf("Método de pago", "Preferencias de usuario")
                )
                PerfilSection(
                    title = "Soporte y Ayuda",
                    items = listOf("Centro de ayuda", "Contáctanos"),
                    onItemClick = { item ->
                        if (item == "Contáctanos") {
                            navController.navigate("contacto")
                        }
                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Neutral30)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(92.dp),
                    contentAlignment = Alignment.Center
                ) {
                    PrimaryButton(
                        text = "Cerrar Sesión",
                        type = ButtonType.SLIM,
                        onClick = { /* TODO */}
                    )
                }
            }
        }
    }
}

// Subcomposable para las secciones
@Composable
fun PerfilSection(
    title: String,
    items: List<String>,
    onItemClick: (String) -> Unit = {}
) {
    val colors = AppTheme.customColors
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = colors.black
        )
        Spacer(modifier = Modifier.height(4.dp))
        items.forEach { item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item) }
                    .padding(vertical = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = colors.black
            )
        }
    }
}
