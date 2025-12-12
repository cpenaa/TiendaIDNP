package com.example.tiendaidnp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tiendaidnp.ui.screens.*
import com.example.tiendaidnp.ui.screens.admin.AdminAddProductScreen
import com.example.tiendaidnp.ui.screens.admin.AdminEditProductScreen
import com.example.tiendaidnp.ui.screens.admin.AdminHubScreen
import com.example.tiendaidnp.ui.screens.admin.AdminOrderListScreen
import com.example.tiendaidnp.ui.screens.admin.AdminProductListScreen
import com.example.tiendaidnp.ui.screens.admin.AdminSendNotificationScreen
import com.example.tiendaidnp.ui.screens.cart.CartScreen
import com.example.tiendaidnp.ui.screens.cart.MyOrdersScreen
import com.example.tiendaidnp.ui.screens.catalog.ProductDetailScreen
import com.example.tiendaidnp.ui.screens.catalog.ProductsScreen
import com.example.tiendaidnp.ui.screens.profile.ContactFormScreen
import com.example.tiendaidnp.ui.screens.profile.EditProfileScreen
import com.example.tiendaidnp.ui.screens.profile.PerfilScreen
import com.example.tiendaidnp.ui.screens.profile.SettingsScreen
import com.example.tiendaidnp.ui.viewmodel.ThemeViewModel
import com.example.tiendaidnp.ui.viewmodel.ThemeViewModelFactory

object Routes {
    const val HOME = "inicio"
    const val PRODUCTS = "productos"
    const val PROFILE = "perfil"
    const val CONTACT = "contacto"
    const val EDIT_PROFILE = "edit_profile"
    const val THEME_SETTINGS = "theme_settings"
    const val ADMIN_HUB = "admin_hub"
    const val ADMIN_ADD_PRODUCT = "admin_add_product"
    const val ADMIN_PRODUCT_LIST = "admin_product_list"
    const val ADMIN_EDIT_PRODUCT = "admin_edit_product/{productId}"
    const val PRODUCT_DETAIL = "product_detail/{productId}"
    const val CART = "cart"
    const val ADMIN_ORDER_LIST = "admin_order_list"
    const val MY_ORDERS = "my_orders"
    const val ADMIN_SEND_NOTIFICATION = "admin_send_notification"
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

        // Pantalla de Administrador para agregar productos
        composable(Routes.ADMIN_ADD_PRODUCT) {
            AdminAddProductScreen()
        }

        // Pantalla de opciones de administrador
        composable(Routes.ADMIN_HUB) {
            AdminHubScreen(navController = navController)
        }

        // Pantalla de lista de productos del administrador
        composable(Routes.ADMIN_PRODUCT_LIST) {
            AdminProductListScreen(navController = navController)
        }

        // Pantalla de edición de producto
        composable(
            route = Routes.ADMIN_EDIT_PRODUCT,
            arguments = listOf(navArgument("productId") { type = NavType.LongType })
        ) {
            val productId = it.arguments?.getLong("productId") ?: 0
            AdminEditProductScreen(navController = navController, productId = productId)
        }

        // Pantalla de detalles del producto
        composable(
            route = Routes.PRODUCT_DETAIL,
            arguments = listOf(navArgument("productId") { type = NavType.LongType })
        ) {
            val productId = it.arguments?.getLong("productId") ?: 0
            ProductDetailScreen(navController = navController, productId = productId)
        }

        // Pantalla del carrito
        composable(Routes.CART) {
            CartScreen(navController = navController)
        }

        // Pantalla de gestión de pedidos del administrador
        composable(Routes.ADMIN_ORDER_LIST) {
            AdminOrderListScreen()
        }

        // Pantalla de mis pedidos
        composable(Routes.MY_ORDERS) {
            MyOrdersScreen(navController = navController)
        }

        // Pantalla para enviar notificaciones
        composable(Routes.ADMIN_SEND_NOTIFICATION) {
            AdminSendNotificationScreen()
        }
    }
}