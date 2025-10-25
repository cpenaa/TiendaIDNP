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
import com.example.tiendaidnp.ui.screens.EditProfileScreen

object Routes {
    const val HOME = "inicio"
    const val PRODUCTS = "productos"
    const val PROFILE = "perfil"
    const val CONTACT = "contacto"
    const val EDIT_PROFILE = "edit_profile"
}
@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        // Pantalla Inicial
        composable(Routes.HOME) {
            PantallaPrincipal(navController = navController)
        }

        // Pantalla de productos
        composable(Routes.PRODUCTS) {
            ProductsScreen(navController = navController)
        }

        // Pantalla de Perfil
        composable(Routes.PROFILE) {
            PerfilScreen(navController = navController)
        }

        // Pantalla de Contacto
        composable(Routes.CONTACT) {
            ContactFormScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // Pantalla de Edición de Perfil
        composable(Routes.EDIT_PROFILE) {
            EditProfileScreen(
                navController = navController,
                onNavigateBack = { navController.popBackStack() }
            )
        }


    }
}
