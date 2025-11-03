package com.example.tiendaidnp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tiendaidnp.ui.screens.PantallaPrincipal
import com.example.tiendaidnp.ui.screens.PerfilScreen
import com.example.tiendaidnp.ui.screens.ProductsScreen
import com.example.tiendaidnp.ui.screens.ContactFormScreen
import com.example.tiendaidnp.ui.screens.EditProfileScreen
import com.example.tiendaidnp.ui.screens.SettingsScreen
import com.example.tiendaidnp.ui.viewmodel.ThemeViewModel
import com.example.tiendaidnp.ui.viewmodel.ThemeViewModelFactory

object Routes {
    const val HOME = "inicio"
    const val PRODUCTS = "productos"
    const val PROFILE = "perfil"
    const val CONTACT = "contacto"
    const val EDIT_PROFILE = "edit_profile"
    const val THEME_SETTINGS = "theme_settings"
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

        // Pantalla de Configuración de Tema
        composable(Routes.THEME_SETTINGS) {
            val themeViewModel: ThemeViewModel = viewModel(
                factory = ThemeViewModelFactory(LocalContext.current)
            )
            SettingsScreen(
                viewModel = themeViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }


    }
}
