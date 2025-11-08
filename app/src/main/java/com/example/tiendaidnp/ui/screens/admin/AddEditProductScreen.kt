package com.example.tiendaidnp.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tiendaidnp.data.model.Product
import com.example.tiendaidnp.ui.components.TitleBackTopBar
import com.example.tiendaidnp.ui.components.buttons.PrimaryButton
import com.example.tiendaidnp.ui.navigation.AdminRoutes
import com.example.tiendaidnp.ui.theme.AppTheme
import com.example.tiendaidnp.ui.viewmodel.ProductsViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditProductScreen(
    navController: NavController,
    viewModel: ProductsViewModel,
    productToEdit: Product? = null
) {
    val scope = rememberCoroutineScope()
    val colors = AppTheme.customColors

    var name by remember { mutableStateOf(TextFieldValue(productToEdit?.name ?: "")) }
    var category by remember { mutableStateOf(TextFieldValue(productToEdit?.category ?: "")) }
    var brand by remember { mutableStateOf(TextFieldValue(productToEdit?.brand ?: "")) }
    var price by remember { mutableStateOf(TextFieldValue(productToEdit?.price?.toString() ?: "")) }
    var oldPrice by remember { mutableStateOf(TextFieldValue(productToEdit?.oldPrice?.toString() ?: "")) }
    var stock by remember { mutableStateOf(TextFieldValue(productToEdit?.stock?.toString() ?: "")) }
    var inOffer by remember { mutableStateOf(productToEdit?.inOffer ?: false) }

    val availableSizes = remember { mutableStateListOf<String>().apply {
        addAll(productToEdit?.availableSizes ?: emptyList())
    } }

    val sku = remember {
        productToEdit?.sku ?: generateSku(brand.text, name.text)
    }

    Scaffold(
        topBar = {
            TitleBackTopBar(
                title = if (productToEdit == null) "Agregar Producto" else "Editar Producto",
                onBackClick = { navController.popBackStack() }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre del producto") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Categoría") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = brand,
                    onValueChange = { brand = it },
                    label = { Text("Marca") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Precio actual") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = oldPrice,
                    onValueChange = { oldPrice = it },
                    label = { Text("Precio anterior (opcional)") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = stock,
                    onValueChange = { stock = it },
                    label = { Text("Stock disponible") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = inOffer, onCheckedChange = { inOffer = it })
                    Text("En oferta")
                }

                Spacer(modifier = Modifier.height(8.dp))

                PrimaryButton(
                    text = if (productToEdit == null) "Agregar" else "Actualizar",
                    onClick = {
                        scope.launch {
                            val product = Product(
                                id = productToEdit?.id ?: 0,
                                sku = sku,
                                name = name.text,
                                category = category.text,
                                brand = brand.text,
                                price = price.text.toDoubleOrNull() ?: 0.0,
                                oldPrice = oldPrice.text.toDoubleOrNull(),
                                inOffer = inOffer,
                                stock = stock.text.toIntOrNull() ?: 0,
                                imagenId = productToEdit?.imagenId ?: android.R.drawable.ic_menu_gallery,
                                availableSizes = availableSizes
                            )

                            if (productToEdit == null)
                                viewModel.addProduct(product)
                            else
                                viewModel.updateProduct(product)

                            navController.popBackStack()
                        }
                    }
                )
            }
        }
    )
}

fun generateSku(brand: String, name: String): String {
    val sdf = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    val date = sdf.format(Date())
    val brandPart = brand.take(3).uppercase(Locale.getDefault())
    val namePart = name.take(3).uppercase(Locale.getDefault())
    val randomNum = (1..999).random().toString().padStart(3, '0')
    return "$brandPart-$namePart-$randomNum-$date"
}
