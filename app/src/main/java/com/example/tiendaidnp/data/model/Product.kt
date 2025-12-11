package com.example.tiendaidnp.data.model

<<<<<<< HEAD
data class Product(
    // Identificación y metadatos
    val id: Long,
=======
import androidx.annotation.DrawableRes

data class Product(
    // Identificación y metadatos
>>>>>>> 5bf2d1506f5e2a78fb77fb3db14961a213ced41e
    val sku: String,                 // Código único (SKU)
    val name: String,
    val category: String,
    val brand: String,

    // Información de precios
    val price: Double,
    val oldPrice: Double?,
    val inOffer: Boolean,
    val stock: Int,

    // Imagen del producto
<<<<<<< HEAD
    val imageUri: String,
=======
    @DrawableRes val imagenId: Int,
>>>>>>> 5bf2d1506f5e2a78fb77fb3db14961a213ced41e

    // Variantes (tallas disponibles)
    val availableSizes: List<String>
)