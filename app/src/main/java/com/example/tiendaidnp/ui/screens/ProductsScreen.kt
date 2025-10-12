package com.example.tiendaidnp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import com.example.tiendaidnp.R
import com.example.tiendaidnp.model.Product
import com.example.tiendaidnp.ui.components.ProductsTopBar
import com.example.tiendaidnp.ui.components.ProductsBottomBar
import com.example.tiendaidnp.ui.components.ProductItem

@Composable
fun ProductsScreen(onNavigate: (String) -> Unit) {
    val products = listOf(
        Product("Producto 1", R.drawable.product_1, 25.50, inOffer = true),
        Product("Producto 2", R.drawable.product_2, 40.00, inOffer = false),
        Product("Producto 3", R.drawable.product_3, 15.99, inOffer = true)
    )

    Scaffold(
        topBar = { ProductsTopBar() },
        bottomBar = {
            ProductsBottomBar(
                selectedIndex = 0,
                onItemSelected = {},
                onNavigate = onNavigate // 👈 Reenvía el callback
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { product ->
                ProductItem(product)
            }
        }
    }
}

