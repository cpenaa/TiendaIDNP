package com.example.tiendaidnp.ui.navigation

import androidx.compose.runtime.remember
// Se elimina @Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavGraphBuilder // Necesario para la función de extensión
// Se elimina import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.get
import com.example.tiendaidnp.data.model.Product
import com.example.tiendaidnp.ui.screens.admin.ProductListScreen
import com.example.tiendaidnp.ui.screens.admin.AddEditProductScreen
import com.example.tiendaidnp.ui.viewmodel.ProductsViewModel
// Importamos NavType para usarlo en argumentos de ruta, si fuera necesario

object AdminRoutes {
    const val PRODUCT_LIST = "admin_product_list"
    const val PRODUCT_FORM = "admin_product_form"
    // Definimos la ruta del grafo principal de Admin para el AppNavigation
    const val ADMIN_GRAPH_ROUTE = "admin_graph_route"
}

// ⚠️ CAMBIO CLAVE: Ya NO es @Composable y es una función de extensión de NavGraphBuilder.
fun NavGraphBuilder.AdminRoutesGraph(navController: NavHostController) {
    // ⚠️ Importante: ¡Aquí NO debe haber un NavHost!

    // Lista de productos (vista administrativa)
    composable(AdminRoutes.PRODUCT_LIST) {
        ProductListScreen(navController = navController)
    }

    // Formulario para agregar/editar producto
    composable(AdminRoutes.PRODUCT_FORM) { backStackEntry ->

        // 🔑 NOTA: La obtención del ViewModel Compartido AHORA usa la ruta del GRAFO principal.
        val parentEntry = remember(backStackEntry) {
            // Utilizamos la ruta del grafo anidado ("admin_graph_route") en lugar de
            // la ruta de la pantalla "admin_product_list" para obtener el ViewModel.
            navController.getBackStackEntry(AdminRoutes.ADMIN_GRAPH_ROUTE)
        }
        val viewModel: ProductsViewModel = viewModel(parentEntry)

        // ✅ Producto a editar (puede ser nulo si es nuevo)
        val productToEdit = backStackEntry.savedStateHandle.get<Product>("productToEdit")

        AddEditProductScreen(
            navController = navController,
            viewModel = viewModel,
            productToEdit = productToEdit
        )
    }
}