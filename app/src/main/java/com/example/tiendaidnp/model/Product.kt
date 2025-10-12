package com.example.tiendaidnp.model

import androidx.annotation.DrawableRes

data class Product(
    val name: String,
    @DrawableRes val imagenId: Int,
    val price: Double,
    val inOffer: Boolean
)