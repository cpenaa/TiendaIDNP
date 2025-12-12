package com.example.tiendaidnp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiendaidnp.data.db.AppDatabase
import com.example.tiendaidnp.data.model.CartItemWithDetails
import com.example.tiendaidnp.data.repository.CartRepository
import com.example.tiendaidnp.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val cartRepository: CartRepository
    private val orderRepository: OrderRepository

    private val _cartItems = MutableStateFlow<List<CartItemWithDetails>>(emptyList())
    val cartItems: StateFlow<List<CartItemWithDetails>> get() = _cartItems

    init {
        val appDatabase = AppDatabase.getDatabase(application)
        cartRepository = CartRepository(appDatabase.cartDao())
        orderRepository = OrderRepository(appDatabase.orderDao(), appDatabase.productDao())
    }

    fun loadCartItems(userId: Long) {
        viewModelScope.launch {
            val cartId = cartRepository.getOrCreateCart(userId)
            cartRepository.getCartItemsWithDetails(cartId)
                .onEach { items ->
                    _cartItems.value = items
                }
                .launchIn(viewModelScope)
        }
    }

    fun addProductToCart(userId: Long, productId: Long, quantity: Int) {
        viewModelScope.launch {
            val cartId = cartRepository.getOrCreateCart(userId)
            cartRepository.addProductToCart(cartId, productId, quantity)
        }
    }

    fun deleteCartItem(cartItemId: Long) {
        viewModelScope.launch {
            cartRepository.deleteCartItem(cartItemId)
        }
    }

    fun placeOrder(userId: Long) {
        viewModelScope.launch {
            val cartId = cartRepository.getOrCreateCart(userId)
            val cartItems = cartRepository.getCartItemsWithDetails(cartId).first()

            if (cartItems.isNotEmpty()) {
                orderRepository.createOrder(userId, cartItems)
                // Limpiar el carrito después de hacer el pedido
                cartItems.forEach { 
                    cartRepository.deleteCartItem(it.cartItemId)
                }
            }
        }
    }
}