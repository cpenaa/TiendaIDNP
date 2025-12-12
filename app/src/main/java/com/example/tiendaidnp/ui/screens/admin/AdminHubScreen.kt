package com.example.tiendaidnp.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tiendaidnp.ui.components.buttons.ButtonType
import com.example.tiendaidnp.ui.components.buttons.PrimaryButton
import com.example.tiendaidnp.ui.navigation.Routes

@Composable
fun AdminHubScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryButton(
            text = "Agregar ropa",
            type = ButtonType.SLIM,
            onClick = { navController.navigate(Routes.ADMIN_ADD_PRODUCT) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            text = "Lista de ropa",
            type = ButtonType.SLIM,
            onClick = { navController.navigate(Routes.ADMIN_PRODUCT_LIST) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            text = "Gestionar pedidos",
            type = ButtonType.SLIM,
            onClick = { navController.navigate(Routes.ADMIN_ORDER_LIST) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            text = "Generar Notificación",
            type = ButtonType.SLIM,
            onClick = { navController.navigate(Routes.ADMIN_SEND_NOTIFICATION) }
        )
    }
}