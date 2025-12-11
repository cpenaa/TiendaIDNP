package com.example.tiendaidnp.data.model

data class Product(
    // Identificación y metadatos
    val id: Long,
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
    val imageUri: String,

    // Variantes (tallas disponibles)
    val availableSizes: List<String>
)