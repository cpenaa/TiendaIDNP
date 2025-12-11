package com.example.tiendaidnp.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert
    suspend fun insertOrder(order: Order): Long

    @Insert
    suspend fun insertOrderItem(orderItem: OrderItem)

    @Query("SELECT * FROM orders WHERE userId = :userId")
    fun getOrdersByUser(userId: Long): Flow<List<Order>>

    @Query("SELECT * FROM orders")
    fun getAllOrders(): Flow<List<Order>>

    @Query("SELECT * FROM order_items WHERE orderId = :orderId")
    suspend fun getOrderItems(orderId: Long): List<OrderItem>

    @Query("UPDATE orders SET status = :status WHERE id = :orderId")
    suspend fun updateOrderStatus(orderId: Long, status: OrderStatus)
}