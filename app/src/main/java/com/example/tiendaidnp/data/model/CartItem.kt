package com.example.tiendaidnp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "cart_items",
    foreignKeys = [
        ForeignKey(entity = Cart::class, parentColumns = ["id"], childColumns = ["cartId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = ProductDB::class, parentColumns = ["id"], childColumns = ["productId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cartId: Long,
    val productId: Long,
    val quantity: Int
)