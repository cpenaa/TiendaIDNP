package com.example.tiendaidnp.ui.screens

import android.app.Application
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.tiendaidnp.ui.viewmodel.AdminViewModel
import com.example.tiendaidnp.ui.viewmodel.AdminViewModelFactory
import com.example.tiendaidnp.ui.viewmodel.ProductsViewModel
import com.example.tiendaidnp.ui.viewmodel.ProductsViewModelFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminEditProductScreen(navController: NavController, productId: Long) {
    val application = LocalContext.current.applicationContext as Application
    val adminViewModel: AdminViewModel = viewModel(factory = AdminViewModelFactory(application))
    val productsViewModel: ProductsViewModel = viewModel(factory = ProductsViewModelFactory(application))

    val product by productsViewModel.getProductById(productId).collectAsState(initial = null)

    var name by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var size by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val categories = listOf("Polos", "Pantalones", "Casacas", "Zapatillas", "Accesorios")
    var expanded by remember { mutableStateOf(false) }
    var category by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(product) {
        product?.let {
            name = it.name
            color = ""
            quantity = it.stock.toString()
            price = it.price.toString()
            size = it.availableSizes.firstOrNull() ?: ""
            category = it.category
            imageUri = Uri.parse(it.imageUri)
        }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
            TextField(value = color, onValueChange = { color = it }, label = { Text("Color") }, modifier = Modifier.fillMaxWidth())
            TextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Cantidad") }, modifier = Modifier.fillMaxWidth())
            TextField(value = price, onValueChange = { price = it }, label = { Text("Precio") }, modifier = Modifier.fillMaxWidth())
            TextField(value = size, onValueChange = { size = it }, label = { Text("Talla") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = category,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Categoría") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                category = selectionOption
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Imagen seleccionada",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(bottom = 16.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Button(onClick = { imagePickerLauncher.launch("image/*") }) {
                Text("Cambiar imagen")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                adminViewModel.updateProduct(
                    productId = productId,
                    name = name,
                    color = color,
                    imageUri = imageUri.toString(),
                    quantity = quantity,
                    price = price,
                    size = size,
                    category = category,
                    onSuccess = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Producto actualizado correctamente")
                            navController.popBackStack()
                        }
                    },
                    onError = { errorMessage ->
                        scope.launch {
                            snackbarHostState.showSnackbar(errorMessage)
                        }
                    }
                )
            }) {
                Text("Guardar cambios")
            }
        }
    }
}