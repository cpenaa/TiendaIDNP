package com.example.tiendaidnp.ui.screens.catalog

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.tiendaidnp.ui.components.ProductsBottomBar
import com.example.tiendaidnp.ui.components.TitleBackTopBar
import com.example.tiendaidnp.ui.components.buttons.PrimaryButton
import com.example.tiendaidnp.ui.viewmodel.CartViewModel
import com.example.tiendaidnp.ui.viewmodel.CartViewModelFactory
import com.example.tiendaidnp.ui.viewmodel.ProductsViewModel
import com.example.tiendaidnp.ui.viewmodel.ProductsViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun ProductDetailScreen(
    navController: NavController,
    productId: Long,
    onNavigateBack: () -> Unit) {
    val application = LocalContext.current.applicationContext as Application
    val productsViewModel: ProductsViewModel = viewModel(factory = ProductsViewModelFactory(application))
    val cartViewModel: CartViewModel = viewModel(factory = CartViewModelFactory(application))
    val product by productsViewModel.getProductById(productId).collectAsState(initial = null)

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TitleBackTopBar(
                title = "Detalle Prenda",
                onBackClick = onNavigateBack
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { ProductsBottomBar(navController = navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            product?.let { p ->
                Image(
                    painter = rememberAsyncImagePainter(p.imageUri),
                    contentDescription = p.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = p.name, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "S/ ${p.price}", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Talla: ${p.availableSizes.firstOrNull() ?: "N/A"}")
                Spacer(modifier = Modifier.height(16.dp))
                PrimaryButton(
                    text = "Agregar al carrito",
                    onClick = {
                        // Usamos un userId fijo (1L) por ahora
                        cartViewModel.addProductToCart(1L, p.id, 1)
                        scope.launch {
                            snackbarHostState.showSnackbar("Producto agregado al carrito")
                        }
                    }
                )
            }
        }
    }
}