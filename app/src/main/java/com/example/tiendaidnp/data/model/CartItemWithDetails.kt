package com.example.tiendaidnp.data.model

data class CartItemWithDetails(
    val cartItemId: Long,
    val productId: Long,
    val name: String,
    val price: Double,
    val imageUri: String,
    val quantity: Int
)