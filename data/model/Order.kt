package com.example.tiendaidnp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class OrderStatus {
    PENDIENTE,
    ACEPTADO,
    RECHAZADO
}

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val status: OrderStatus
)