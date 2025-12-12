package com.example.tiendaidnp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiendaidnp.data.db.AppDatabase
import com.example.tiendaidnp.data.model.Order
import com.example.tiendaidnp.data.model.OrderStatus
import com.example.tiendaidnp.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val orderRepository: OrderRepository

    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> get() = _orders

    init {
        val appDatabase = AppDatabase.getDatabase(application)
        orderRepository = OrderRepository(appDatabase.orderDao(), appDatabase.productDao())
    }

    fun loadOrdersByUser(userId: Long) {
        orderRepository.getOrdersByUser(userId)
            .onEach { orderList ->
                _orders.value = orderList
            }
            .launchIn(viewModelScope)
    }

    fun loadAllOrders() {
        orderRepository.getAllOrders()
            .onEach { orderList ->
                _orders.value = orderList
            }
            .launchIn(viewModelScope)
    }

    fun updateOrderStatus(orderId: Long, status: OrderStatus) {
        viewModelScope.launch {
            orderRepository.updateOrderStatus(orderId, status)
        }
    }
}