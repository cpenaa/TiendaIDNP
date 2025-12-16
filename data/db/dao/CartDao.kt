package com.example.tiendaidnp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tiendaidnp.data.db.entities.Cart
import com.example.tiendaidnp.data.db.entities.CartItem
import com.example.tiendaidnp.data.model.CartItemWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cart_items WHERE cartId = :cartId")
    fun getCartItems(cartId: Long): Flow<List<CartItem>>

    @Query("""
        SELECT 
            ci.id as cartItemId, 
            p.id as productId, 
            p.name, 
            p.price, 
            p.imageUri, 
            ci.quantity 
        FROM cart_items as ci 
        INNER JOIN products as p ON ci.productId = p.id 
        WHERE ci.cartId = :cartId
    """)
    fun getCartItemsWithDetails(cartId: Long): Flow<List<CartItemWithDetails>>

    @Query("DELETE FROM cart_items WHERE id = :cartItemId")
    suspend fun deleteCartItem(cartItemId: Long)

    @Query("SELECT * FROM carts WHERE userId = :userId LIMIT 1")
    suspend fun getCartByUserId(userId: Long): Cart?

    @Insert
    suspend fun insertCart(cart: Cart): Long
}