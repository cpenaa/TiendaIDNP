package com.example.tiendaidnp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tiendaidnp.R
import com.example.tiendaidnp.ui.components.buttons.PrimaryButton
import com.example.tiendaidnp.ui.components.ProductsBottomBar
import com.example.tiendaidnp.ui.components.ProductsTopBar
import com.example.tiendaidnp.ui.components.ScreenTitle
import com.example.tiendaidnp.ui.components.buttons.ButtonType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = { ProductsTopBar() },
        bottomBar = {
            ProductsBottomBar(
                selectedIndex = 0,
                onItemSelected = {},
                onNavigate = onNavigate
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle()
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
                        text = "Gabriela Fernandez",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Editar perfil",
                        modifier = Modifier
                            .size(18.dp)
                            .alpha(0.5f)
                    )
                }
                Text(
                    text = "gabriela.fernandez@gmail.com",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.surface
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
                            onNavigate("contacto")
                        }
                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.outline)
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.outlineVariant
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
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }
    }
}
