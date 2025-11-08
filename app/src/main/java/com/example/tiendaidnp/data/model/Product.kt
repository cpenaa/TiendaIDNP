package com.example.tiendaidnp.data.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

/**
 * Modelo de producto unificado (UI + Persistencia)
 */
@Entity(tableName = "products")
@TypeConverters(ProductConverters::class)
data class Product(
    // 🔹 Identificador único (autogenerado por Room)
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    // 🔹 Identificación y metadatos
    val sku: String,          // Código único (SKU)
    val name: String,
    val category: String,
    val brand: String,

    // 🔹 Información de precios
    val price: Double,
    val oldPrice: Double? = null,
    val inOffer: Boolean = false,
    val stock: Int = 0,

    // 🔹 Imagen del producto
    @DrawableRes val imagenId: Int = 0,

    // 🔹 Variantes o tallas
    val availableSizes: List<String> = emptyList()
)

/**
 * Conversores necesarios para Room (listas, etc.)
 */
class ProductConverters {

    @TypeConverter
    fun fromSizesList(value: List<String>?): String {
        return value?.joinToString(",") ?: ""
    }

    @TypeConverter
    fun toSizesList(value: String): List<String> {
        return if (value.isEmpty()) emptyList() else value.split(",")
    }
}
