package com.example.tiendaidnp.ui.screens

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.tiendaidnp.data.model.CartItemWithDetails
import com.example.tiendaidnp.ui.components.ProductsBottomBar
import com.example.tiendaidnp.ui.components.ScreenTitle
import com.example.tiendaidnp.ui.components.buttons.PrimaryButton
import com.example.tiendaidnp.ui.viewmodel.CartViewModel
import com.example.tiendaidnp.ui.viewmodel.CartViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun CartScreen(navController: NavController) {
    val application = LocalContext.current.applicationContext as Application
    val cartViewModel: CartViewModel = viewModel(factory = CartViewModelFactory(application))
    val cartItems by cartViewModel.cartItems.collectAsState()

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val total = cartItems.sumOf { it.price * it.quantity }

    LaunchedEffect(Unit) {
        cartViewModel.loadCartItems(1L) // Usamos un userId fijo (1L) por ahora
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { ProductsBottomBar(navController = navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ScreenTitle(title = "Mi Carrito")
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                items(cartItems) { item ->
                    CartListItem(item = item, onDelete = { cartViewModel.deleteCartItem(item.cartItemId) })
                }
            }
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total", style = MaterialTheme.typography.headlineSmall)
                    Text("S/ $total", style = MaterialTheme.typography.headlineSmall)
                }
                Spacer(modifier = Modifier.height(16.dp))
                PrimaryButton(
                    text = "Hacer pedido",
                    onClick = {
                        cartViewModel.placeOrder(1L) // Usamos un userId fijo (1L) por ahora
                        scope.launch {
                            snackbarHostState.showSnackbar("Pedido realizado correctamente")
                        }
                    },
                    enabled = cartItems.isNotEmpty()
                )
            }
        }
    }
}

@Composable
fun CartListItem(item: CartItemWithDetails, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(item.imageUri),
                contentDescription = item.name,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = item.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = "S/ ${item.price}")
                Text(text = "Cantidad: ${item.quantity}")
            }
        }
        IconButton(onClick = onDelete) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
        }
    }
}