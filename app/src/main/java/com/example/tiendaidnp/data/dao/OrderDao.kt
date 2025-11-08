package com.example.tiendaidnp.data.dao

import androidx.room.*
import com.example.tiendaidnp.data.model.AppOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders ORDER BY id DESC")
    fun getAllOrders(): Flow<List<AppOrder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: AppOrder)
}
