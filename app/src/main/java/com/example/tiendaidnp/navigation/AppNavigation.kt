package com.example.tiendaidnp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tiendaidnp.PantallaPrincipal
import com.example.tiendaidnp.ProductosScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "pantalla_principal"
    ) {
        // Pantalla inicial
        composable("pantalla_principal") {
            PantallaPrincipal(
                onNavigate = { navController.navigate("productos") }
            )
        }

        // Pantalla de productos
        composable("productos") {
            ProductosScreen()
        }
    }
}
