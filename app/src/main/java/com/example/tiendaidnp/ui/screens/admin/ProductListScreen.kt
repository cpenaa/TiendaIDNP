package com.example.tiendaidnp.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiendaidnp.ui.components.ProductItem
import com.example.tiendaidnp.ui.viewmodel.ProductsViewModel
import kotlinx.coroutines.launch
import androidx.navigation.NavController
import com.example.tiendaidnp.ui.components.buttons.PrimaryButton
import com.example.tiendaidnp.ui.navigation.AdminRoutes
import com.example.tiendaidnp.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductsViewModel = viewModel()
) {
    val products by viewModel.products.collectAsState()
    val scope = rememberCoroutineScope()
    val colors = AppTheme.customColors

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp, 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Administrar productos",
                style = MaterialTheme.typography.titleMedium,
                color = colors.primary80
            )

            PrimaryButton(
                text = "Agregar",
                onClick = {
                    // Limpia producto a editar (nuevo producto)
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("productToEdit", null)

                    // Navega al formulario
                    navController.navigate(AdminRoutes.PRODUCT_FORM)
                }
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(products) { prod ->
                ProductItem(
                    producto = prod,
                    isAdmin = true,
                    onEditClick = {
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("productToEdit", prod)

                        navController.navigate(AdminRoutes.PRODUCT_FORM)
                    },
                    onDeleteClick = {
                        scope.launch {
                            viewModel.deleteProduct(prod.id)
                        }
                    }
                )
            }
        }
    }
}