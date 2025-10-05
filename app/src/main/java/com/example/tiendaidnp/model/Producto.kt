package com.example.tiendaidnp.model

import androidx.annotation.DrawableRes

data class Producto(
    val nombre: String,
    @DrawableRes val imagenId: Int,
    val precio: Double,
    val enOferta: Boolean
)