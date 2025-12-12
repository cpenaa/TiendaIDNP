package com.example.tiendaidnp.data.repository

import com.example.tiendaidnp.data.model.CartItemWithDetails
import com.example.tiendaidnp.data.model.Order
import com.example.tiendaidnp.data.db.dao.OrderDao
import com.example.tiendaidnp.data.db.entities.OrderItem
import com.example.tiendaidnp.data.model.OrderStatus
import com.example.tiendaidnp.data.db.dao.ProductDao
import kotlinx.coroutines.flow.Flow

class OrderRepository(private val orderDao: OrderDao, private val productDao: ProductDao) {

    fun getAllOrders(): Flow<List<Order>> {
        return orderDao.getAllOrders()
    }

    fun getOrdersByUser(userId: Long): Flow<List<Order>> {
        return orderDao.getOrdersByUser(userId)
    }

    suspend fun createOrder(userId: Long, cartItems: List<CartItemWithDetails>) {
        val orderId = orderDao.insertOrder(Order(userId = userId, status = OrderStatus.PENDIENTE))

        cartItems.forEach { cartItem ->
            val orderItem = OrderItem(
                orderId = orderId,
                productId = cartItem.productId,
                quantity = cartItem.quantity,
                price = cartItem.price
            )
            orderDao.insertOrderItem(orderItem)
        }
    }

    suspend fun updateOrderStatus(orderId: Long, status: OrderStatus) {
        orderDao.updateOrderStatus(orderId, status)

        if (status == OrderStatus.ACEPTADO) {
            val orderItems = orderDao.getOrderItems(orderId)
            orderItems.forEach { 
                productDao.updateStock(it.productId, it.quantity)
            }
        }
    }
}