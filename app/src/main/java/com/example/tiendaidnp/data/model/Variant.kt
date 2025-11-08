package com.example.tiendaidnp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "variants")
data class Variant(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId: Int,
    val color: String,
    val size: String,
    val stock: Int
)
