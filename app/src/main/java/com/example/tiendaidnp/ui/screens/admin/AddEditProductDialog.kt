package com.example.tiendaidnp.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.tiendaidnp.data.model.Product

@Composable
fun AddEditProductDialog(
    initialProduct: Product? = null,
    onDismiss: () -> Unit,
    onConfirm: (Product) -> Unit
) {
    var sku by remember { mutableStateOf(TextFieldValue(initialProduct?.sku ?: "")) }
    var name by remember { mutableStateOf(TextFieldValue(initialProduct?.name ?: "")) }
    var category by remember { mutableStateOf(TextFieldValue(initialProduct?.category ?: "")) }
    var brand by remember { mutableStateOf(TextFieldValue(initialProduct?.brand ?: "")) }
    var price by remember { mutableStateOf(TextFieldValue(initialProduct?.price?.toString() ?: "")) }
    var oldPrice by remember { mutableStateOf(TextFieldValue(initialProduct?.oldPrice?.toString() ?: "")) }
    var inOffer by remember { mutableStateOf(initialProduct?.inOffer ?: false) }
    var stock by remember { mutableStateOf(TextFieldValue(initialProduct?.stock?.toString() ?: "")) }
    var sizes by remember { mutableStateOf(TextFieldValue(initialProduct?.availableSizes?.joinToString(", ") ?: "")) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(if (initialProduct == null) "Agregar Producto" else "Editar Producto")
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = sku,
                    onValueChange = { sku = it },
                    label = { Text("SKU") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre") },
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
                    label = { Text("Precio (S/)") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = oldPrice,
                    onValueChange = { oldPrice = it },
                    label = { Text("Precio anterior (opcional)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Checkbox(
                        checked = inOffer,
                        onCheckedChange = { inOffer = it }
                    )
                    Text("En oferta")
                }

                OutlinedTextField(
                    value = stock,
                    onValueChange = { stock = it },
                    label = { Text("Stock") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = sizes,
                    onValueChange = { sizes = it },
                    label = { Text("Tallas disponibles (separadas por coma)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val product = Product(
                    sku = sku.text,
                    name = name.text,
                    category = category.text,
                    brand = brand.text,
                    price = price.text.toDoubleOrNull() ?: 0.0,
                    oldPrice = oldPrice.text.toDoubleOrNull(),
                    inOffer = inOffer,
                    stock = stock.text.toIntOrNull() ?: 0,
                    imagenId = initialProduct?.imagenId ?: 0, // por ahora dejamos 0
                    availableSizes = sizes.text.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                )
                onConfirm(product)
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancelar")
            }
        }
    )
}
