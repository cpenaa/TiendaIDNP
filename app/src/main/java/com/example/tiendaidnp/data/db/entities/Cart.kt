package com.example.tiendaidnp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carts")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long // O alguna forma de identificar al usuario
)