package com.example.tiendaidnp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tiendaidnp.ui.screens.PantallaPrincipal
import com.example.tiendaidnp.ui.screens.PerfilScreen
import com.example.tiendaidnp.ui.screens.ProductsScreen
import com.example.tiendaidnp.ui.screens.ContactFormScreen

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
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        // Pantalla de productos
        composable("productos") {
            ProductsScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        // Pantalla de Perfil
        composable("perfil") {
            PerfilScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        // Pantalla de Contacto
        composable("contacto") {
            ContactFormScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
