package com.example.tiendaidnp.data.repository

import com.example.tiendaidnp.data.model.Cart
import com.example.tiendaidnp.data.model.CartDao
import com.example.tiendaidnp.data.model.CartItem
import com.example.tiendaidnp.data.model.CartItemWithDetails
import kotlinx.coroutines.flow.Flow

class CartRepository(private val cartDao: CartDao) {

    suspend fun getOrCreateCart(userId: Long): Long {
        val cart = cartDao.getCartByUserId(userId)
        return cart?.id ?: cartDao.insertCart(Cart(userId = userId))
    }

    fun getCartItemsWithDetails(cartId: Long): Flow<List<CartItemWithDetails>> {
        return cartDao.getCartItemsWithDetails(cartId)
    }

    suspend fun addProductToCart(cartId: Long, productId: Long, quantity: Int) {
        val cartItem = CartItem(cartId = cartId, productId = productId, quantity = quantity)
        cartDao.insertCartItem(cartItem)
    }

    suspend fun deleteCartItem(cartItemId: Long) {
        cartDao.deleteCartItem(cartItemId)
    }
}