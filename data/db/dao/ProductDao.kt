package com.example.tiendaidnp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tiendaidnp.data.db.entities.ProductDB
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert
    suspend fun insertProduct(product: ProductDB)

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductDB>>

    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun deleteProductById(productId: Long)

    @Query("SELECT * FROM products WHERE id = :productId")
    fun getProductById(productId: Long): Flow<ProductDB>

    @Query("UPDATE products SET quantity = quantity - :quantity WHERE id = :productId")
    suspend fun updateStock(productId: Long, quantity: Int)

    @Update
    suspend fun updateProduct(product: ProductDB)
}