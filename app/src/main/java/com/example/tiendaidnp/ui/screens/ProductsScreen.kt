package com.example.tiendaidnp.ui.screens

<<<<<<< HEAD
import android.app.Application
=======
>>>>>>> 5bf2d1506f5e2a78fb77fb3db14961a213ced41e
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
<<<<<<< HEAD
import androidx.compose.ui.platform.LocalContext
=======
>>>>>>> 5bf2d1506f5e2a78fb77fb3db14961a213ced41e
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tiendaidnp.ui.components.FilterBar
import com.example.tiendaidnp.ui.components.ProductsTopBar
import com.example.tiendaidnp.ui.components.ProductsBottomBar
import com.example.tiendaidnp.ui.components.ProductItem
import com.example.tiendaidnp.ui.viewmodel.ProductsViewModel
<<<<<<< HEAD
import com.example.tiendaidnp.ui.viewmodel.ProductsViewModelFactory
=======
>>>>>>> 5bf2d1506f5e2a78fb77fb3db14961a213ced41e

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
<<<<<<< HEAD
    navController: NavController
) {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: ProductsViewModel = viewModel(
        factory = ProductsViewModelFactory(application)
    )
=======
    navController: NavController,
    viewModel: ProductsViewModel = viewModel()
) {
>>>>>>> 5bf2d1506f5e2a78fb77fb3db14961a213ced41e
    // Estado del ViewModel
    val products by viewModel.products.collectAsState()

    // Filtro de categoría
    var selectedCategory by remember { mutableStateOf("Todos") }

    Scaffold(
        topBar = { ProductsTopBar() },
        bottomBar = { ProductsBottomBar(navController = navController) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp, 0.dp)
        ) {
            // Barra de filtros
            FilterBar(
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Lista dinámica de productos
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                val filteredProducts = if (selectedCategory == "Todos") {
                    products
                } else {
                    products.filter { it.category == selectedCategory }
                }

                itemsIndexed(filteredProducts) { index, product ->
                    val verticalShift = if (index % 2 == 1) 40.dp else 0.dp // desfase en la 2da columna

                    Box(
                        modifier = Modifier
                            .offset(y = verticalShift) // 👈 mueve visualmente sin afectar layout
                    ) {
<<<<<<< HEAD
                        ProductItem(navController = navController, producto = product)
=======
                        ProductItem(product)
>>>>>>> 5bf2d1506f5e2a78fb77fb3db14961a213ced41e
                    }
                }
            }
        }
    }
}

