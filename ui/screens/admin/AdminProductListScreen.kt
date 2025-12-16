package com.example.tiendaidnp.ui.screens.admin

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tiendaidnp.data.model.Product
import com.example.tiendaidnp.ui.navigation.Routes
import com.example.tiendaidnp.ui.viewmodel.ProductsViewModel
import com.example.tiendaidnp.ui.viewmodel.ProductsViewModelFactory

@Composable
fun AdminProductListScreen(navController: NavController) {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: ProductsViewModel = viewModel(
        factory = ProductsViewModelFactory(application)
    )
    val products by viewModel.products.collectAsState()

    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            items(products) { product ->
                ProductListItem(
                    product = product,
                    onEdit = { navController.navigate(Routes.ADMIN_EDIT_PRODUCT.replace("{productId}", product.id.toString())) },
                    onDelete = { viewModel.deleteProduct(product.id) }
                )
            }
        }
    }
}

@Composable
fun ProductListItem(product: Product, onEdit: () -> Unit, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = product.name)
        Row {
            Button(onClick = onEdit) {
                Text("Editar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onDelete) {
                Text("Eliminar")
            }
        }
    }
}