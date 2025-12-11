package com.example.tiendaidnp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductDB(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val color: String,
    val imageUri: String,
    val quantity: Int,
    val price: Double,
    val size: String,
    val category: String
)